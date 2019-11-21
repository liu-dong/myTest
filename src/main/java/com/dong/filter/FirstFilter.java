package com.dong.filter;

import javax.servlet.*;
import java.io.IOException;

public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------------初始化过滤器------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------------进入过滤器------------");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("------------退出过滤器------------");
    }

    @Override
    public void destroy() {
        System.out.println("------------注销过滤器------------");
    }
}
