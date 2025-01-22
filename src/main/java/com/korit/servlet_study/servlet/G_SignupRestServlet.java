package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.dto.G_SignupDto;
import com.korit.servlet_study.entity.G_User;
import com.korit.servlet_study.service.G_AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signup")
public class G_SignupRestServlet extends HttpServlet {
    private G_AuthService authService;

    public G_SignupRestServlet() {
        authService = G_AuthService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //한줄만 받는다면 String line = bufferedReader.readLine() 이걸로 충분

        //문자열 합치기
        StringBuilder requsetJsonData = new StringBuilder();
        //json 받기
        try(BufferedReader bufferedReader = request.getReader()) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                requsetJsonData.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        G_SignupDto signupDto = objectMapper.readValue(requsetJsonData.toString(), G_SignupDto.class);

        G_ResponseDto<?> responseDto = authService.signup(signupDto);
        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseDto));

        //resp.sendRedirect("http://localhost:8080/servlet_study_war/api/board");


    }
}
