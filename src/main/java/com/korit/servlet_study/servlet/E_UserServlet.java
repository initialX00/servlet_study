package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.E_User;

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

@WebServlet("/user")
public class E_UserServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        List<E_User> users = new ArrayList<>();
        users.add(new E_User(0, "aaa","111","qwer","aaaa@naver.com"));
        users.add(new E_User(0, "bbb","222","asdf","aaaa@naver.com"));
        users.add(new E_User(0, "ccc","333","zxcv","aaaa@naver.com"));
        users.add(new E_User(0, "ddd","444","tyui","aaaa@naver.com"));
        users.add(new E_User(0, "eee","555","ghjk","aaaa@naver.com"));
        config.getServletContext().setAttribute("users", users);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchValue = request.getParameter("searchValue");
        ServletContext servletContext = request.getServletContext();
        List<E_User> users = (List<E_User>) servletContext.getAttribute("users");

        if(searchValue != null) {
            if(searchValue.isBlank()) {
                request.setAttribute("users", users.stream()
                        .filter(user -> user.getUsername()
                                .contains(searchValue))
                        .collect(Collectors.toList()));
            }
        }
        request.getRequestDispatcher("/WEB-INF/e_user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        E_User user = E_User.builder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .build();

        ServletContext servletContext = request.getServletContext();

        List<E_User> users = (List<E_User>) servletContext.getAttribute("users");
        if(users.stream().filter(u -> u.getUsername()
                .equals(user.getUsername()))
                .collect(Collectors.toList()).size() > 0) {
            response.setContentType("text/html");
            response.getWriter().println("<script>" +
                    "alert('이미 존재하는 사용자 이름입니다.');" +
                    "history.back();" +
                    "</script>");
            return;
        }
        users.add(user);

        response.sendRedirect("http://localhost:8080/servlet_study_war/user");
    }
}
