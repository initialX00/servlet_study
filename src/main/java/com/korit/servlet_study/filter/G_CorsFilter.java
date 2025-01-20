package com.korit.servlet_study.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//모든경로에 대해 필터 사용
@WebFilter("*")
public class G_CorsFilter implements Filter {

    //CORS과련 HTTP헤더를 응답에 추가
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;  //다운캐스팅

        response.setHeader("Access-Control-Allow-Origin", "*"); //모든 도메인에서 해당 리소스에 접근허용
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); //서버에 요청할 수 있는  http메서드 지정
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); //요청 시 서버로 보낼 수 있는 헤더들을 지정
        response.setHeader("Access-Control-Allow-Credentials", "true"); //클라이언트가 인증된 사용자로 요청을 보낼 수 있게한다. true시 쿠키나 http인증 정보도 포함한다.

        filterChain.doFilter(servletRequest, servletResponse);
        //리액트 - 톰캣 - 필터 - 서블렛 - 서비스 - 리포지터리 - db
    }

    //필터 초기화 메서드
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    //필터 제거 메서드
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
