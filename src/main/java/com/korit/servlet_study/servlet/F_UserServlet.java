package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.E_User;
import com.korit.servlet_study.service.F_UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user2")
public class F_UserServlet extends HttpServlet {

    private F_UserService userService;

    public F_UserServlet() {
        userService = F_UserService.getInstance();
    }

    //저장된 값 홈페이지에 띄우기
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchValue = request.getParameter("searchValue");

        request.setAttribute("users", userService.getAllUsers(searchValue));

        request.getRequestDispatcher("/WEB-INF/f_user.jsp").forward(request, response);
    }

    //입력한 정보 데이터베이스에 저장
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        E_User user = E_User.builder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .build();

        userService.addUser(user);

        response.sendRedirect("http://localhost:8080/servlet_study_war/user2");
    }
}
