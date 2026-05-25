package com.test.student.interceptor;

import com.test.student.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tools.jackson.databind.ObjectMapper;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private  final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String username = null;
        if (session != null) {
            username = (String) session.getAttribute("username");
        }

        if (username == null || username.equals("")) {
            // 判断是否为 AJAX 请求
            String requestedWith = request.getHeader("X-Requested-With");
            boolean isAjax = "XMLHttpRequest".equals(requestedWith);

            if (isAjax) {
                // AJAX 请求返回 JSON
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                Result<Object> result = Result.failed("没登陆，请先登录！");
                response.getWriter().write(objectMapper.writeValueAsString(result));
            } else {
                // 普通表单请求重定向到登录页
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
            return false;
        }
        return true;
    }
}
