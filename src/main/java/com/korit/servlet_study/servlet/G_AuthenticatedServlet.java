package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.security.jwt.G_JwtProvider;
import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/authenticated")
public class G_AuthenticatedServlet extends HttpServlet {
    private G_JwtProvider jwtProvider;

    public G_AuthenticatedServlet() {
        jwtProvider = G_JwtProvider.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToken = req.getHeader("Authorization");
        System.out.println(bearerToken);
        ObjectMapper objectMapper = new ObjectMapper();
        G_ResponseDto<?> responseDto = null;//

        if(bearerToken == null) { //jwp 토큰을 식별하기 위해 bearer를 기입한다.
            responseDto = G_ResponseDto.forbidden("검증 할 수 없는 Access Token 입니다.");
            resp.setStatus(responseDto.getStatus());
            resp.setContentType("application/json");
            resp.getWriter().println(objectMapper.writeValueAsString(responseDto));
            return;
        }

        Claims claims = jwtProvider.parseToken(bearerToken);
        if(claims == null) {
            responseDto = G_ResponseDto.forbidden("검증 할 수 없는 Access Token 입니다.");
            resp.setStatus(responseDto.getStatus());
            resp.setContentType("application/json");
            resp.getWriter().println(objectMapper.writeValueAsString(responseDto));
            return;
        }

        responseDto = G_ResponseDto.success(claims.get("userId"));
        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(objectMapper.writeValueAsString(responseDto));
    }
}
