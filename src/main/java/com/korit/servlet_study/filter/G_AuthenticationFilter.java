package com.korit.servlet_study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.G_UserDao;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.entity.G_User;
import com.korit.servlet_study.security.annotation.G_JwtValid;
import com.korit.servlet_study.security.jwt.G_JwtProvider;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


//web.xml에서 설정하여 생략. filter-mapping 순서에 따라 필터 적용
//@WebFilter({"/api/user/*"})
public class G_AuthenticationFilter implements Filter {
    private G_JwtProvider jwtProvider;
    private G_UserDao userDao;

    public G_AuthenticationFilter() {
        jwtProvider = G_JwtProvider.getInstance();
        userDao = G_UserDao.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            if(isJwtTokenValid(request)) {
                String bearerToken = request.getHeader("Authorization");
                if(bearerToken == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }

                Claims claims = jwtProvider.parseToken(bearerToken);
                if(claims == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }

                int userId = Integer.parseInt(claims.get("userId").toString());
                G_User foundUser = userDao.findById(userId);
                if(foundUser == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private boolean isJwtTokenValid(HttpServletRequest request) throws ClassNotFoundException {
        String method = request.getMethod();
        String servletPath = request.getHttpServletMapping().getServletName();

        Class<?> servletClass = Class.forName(servletPath); //throws ClassNotFoundException
        Method foundMethod = getMappedMethod(servletClass, method);
        return foundMethod != null;
    }

    private Method getMappedMethod(Class<?> clazz, String methodName) { //클래스클래스 : 해당 클래스 이름으로 클래스 객체생성
        for(Method method : clazz.getDeclaredMethods()) { //클래스 내의 모든 메서드를 메서드 객체로서 불러오기
            if(method.getName().toLowerCase().endsWith(methodName.toLowerCase()) && method.isAnnotationPresent(G_JwtValid.class)) {
                return method;
            }
        }
        return null;
    }

    private void setUnAuthenticatedResponse(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        G_ResponseDto<String> responseDto = G_ResponseDto.forbidden("검증 할 수 없는 Access Token 입니다.");

        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(responseDto)); //throws IOException
    }
}
