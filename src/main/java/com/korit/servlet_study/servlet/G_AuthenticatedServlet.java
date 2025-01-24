package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.filter.G_AuthenticationFilter;
import com.korit.servlet_study.security.annotation.G_JwtValid;
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

    @G_JwtValid
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToken = req.getHeader("Authorization");

        ObjectMapper objectMapper = new ObjectMapper();
        Claims claims = jwtProvider.parseToken(bearerToken);
        G_ResponseDto<?> responseDto = G_ResponseDto.success(claims.get("userId"));

        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(objectMapper.writeValueAsString(responseDto));
    }
}
