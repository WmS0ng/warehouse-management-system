package com.example.warehouse.controller;

import com.example.warehouse.entity.CurrentUser;
import com.example.warehouse.entity.LoginUser;
import com.example.warehouse.entity.User;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.DigestUtil;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import com.google.code.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    // 注入DefaultKaptcha物bean对象 -- 生成验证码图片
    @Autowired
    private Producer producer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 生成验证码图片的url接口
     */
    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            // 先生成验证码图片的文本
            String text = producer.createText();
            // 使用验证码文本生成验证码图片 -- BufferedImage对象代表生成的验证码图片，在内存中
            BufferedImage image = producer.createImage(text);
            // 将验证码文本作为键保存到Redis -- 设置键的过期时间为1分钟
            stringRedisTemplate.opsForValue().set(text, "", 60, TimeUnit.SECONDS);

            // 将验证码图片响应该前端
            // 设置响应正文image/jpeg
            response.setContentType("image/jpeg");
            // 将验证码图片写给前端
            outputStream = response.getOutputStream();
            // 使用响应对象的字节输出流写入验证码图片，自然是写给前端
            ImageIO.write(image, "jpg", outputStream);
            // 刷新
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭字节输出流
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 登陆url接口login
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {
        // 验证码不正确
        if (!Boolean.TRUE.equals(stringRedisTemplate.hasKey(loginUser.getVerificationCode()))) {
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码不正确！");
        }
        // 查询数据库
        User user = userService.selectUserByCode(loginUser.getUserCode());
        // 用户不存在
        if (user == null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "账号不存在！");
        }
        // 用户未审核
        if (user.getUserState().equals(WarehouseConstants.USER_STATE_NOT_PASS)) {
            return Result.err(Result.CODE_ERR_BUSINESS, "用户未审核！");
        }
        // 拿到用户录入的密码并进行加密
        String userPwd = DigestUtil.hmacSign(loginUser.getUserPwd());
        // 用户密码错误
        if (!user.getUserPwd().equals(userPwd)) {
            return Result.err(Result.CODE_ERR_BUSINESS, "用户密码错误！");
        }
        // 生成jwt token，并保存到Redis
        CurrentUser currentUser = new CurrentUser(user.getUserId(), user.getUserCode(), user.getUserName());
        String token = tokenUtils.loginSign(currentUser, userPwd);
        // 想客户端响应jwt token1
        return Result.ok("登陆成功！", token);
    }
}