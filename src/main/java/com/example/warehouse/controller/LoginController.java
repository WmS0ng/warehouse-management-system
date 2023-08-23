package com.example.warehouse.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    // 注入DefaultKaptcha物bean对象 -- 生成验证码图片
    @Autowired
    private Producer producer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            // 先生成验证码图片的文本
            String text = producer.createText();
            // 使用验证码文本生成验证码图片 -- BufferedImage对象代表生成的验证码图片，在内存中
            BufferedImage image = producer.createImage(text);
            // 将验证码文本作为键保存到Redis -- 设置键的过期时间为5分钟
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
}