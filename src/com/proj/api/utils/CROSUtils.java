package com.proj.api.utils;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jangitlau on 2017/11/4.
 */
public class CROSUtils implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("====== 已开启跨域访问 ======");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println(req.getRemoteAddr());
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Max-Age", "999999");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, json");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
