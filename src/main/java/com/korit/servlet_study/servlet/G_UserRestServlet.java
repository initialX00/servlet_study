package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.entity.G_User;
import com.korit.servlet_study.entity.G_Book;
import com.korit.servlet_study.security.annotation.G_JwtValid;
import com.korit.servlet_study.service.G_UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class G_UserRestServlet extends HttpServlet {

    private G_UserService userService;

    public G_UserRestServlet() {
        userService = G_UserService.getInstance();
    }

    @G_JwtValid
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);
        G_ResponseDto responseDto = userService.getUser(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(responseDto);
        System.out.println(jsonUser);

        response.setContentType("application/json");
        response.getWriter().println(jsonUser);
    }
}
