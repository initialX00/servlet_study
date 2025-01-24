package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.dto.G_SigninDto;
import com.korit.servlet_study.service.G_AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signin")
public class G_SigninRestServlet extends HttpServlet {

    private G_AuthService authService;

    public G_SigninRestServlet() {
        authService = G_AuthService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try(BufferedReader reader = request.getReader();) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        //json문자열을 Dto로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        G_SigninDto signinDto = objectMapper.readValue(stringBuilder.toString(), G_SigninDto.class);

        G_ResponseDto<?> responseDto = authService.signin(signinDto);

        response.setContentType("application/json");
        response.setStatus(responseDto.getStatus());
        //Dto를 json으로 변환
        response.getWriter().write(objectMapper.writeValueAsString(responseDto));

    }
}
