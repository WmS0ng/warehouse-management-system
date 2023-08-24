package com.example.warehouse.filter;

import com.alibaba.fastjson2.JSON;
import com.example.warehouse.result.Result;
import com.example.warehouse.utils.WarehouseConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LoginCheckFilter implements Filter {

    // 过滤器拦截的请求执行方法
    private StringRedisTemplate stringRedisTemplate;

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 白名单请求直接放行
        // 创建list集合，放白名单url
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/captcha/captchaImage");
        urls.add("/login");
        // 过滤器拦截到的当前请求的url接口
        String url = request.getServletPath();
        if (urls.contains(url)) {
            chain.doFilter(request, response);
            return;
        }
        // 其他请求是否携带token，携带的token是否在redis中存在
        String token = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        // redis中存在该请求携带的token，放行
        if (StringUtils.hasText(token) && Boolean.TRUE.equals(stringRedisTemplate.hasKey(token))) {
            chain.doFilter(request, response);
            return;
        }
        // redis中不存在该请求携带的token，拦截
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "您尚未登陆");
        String jsonString = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.flush();
        out.close();
    }
}
