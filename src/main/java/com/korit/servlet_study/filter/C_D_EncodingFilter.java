package com.korit.servlet_study.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//웹자체 필터를 줘서 클래스에서 따로 호출 하지 않는다
//@WebFilter("*")
public class C_D_EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터동작");
        HttpServletRequest request = (HttpServletRequest) servletRequest; //다운캐스팅
        servletRequest.setCharacterEncoding("UTF-8"); //요청 한글 적용
        servletResponse.setCharacterEncoding("UTF-8"); //응답 한글 적용

        //처리 전
        filterChain.doFilter(servletRequest, servletResponse); //doGet //
        //처리 후

        System.out.println("후처리");
    }
}
