package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.E_User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class E_UserServlet {

    public void init(ServletConfig config) throws ServletException {
        List<E_User> users = new ArrayList<>();
        users.add(new E_User("aaa","111","qwer","aaaa@naver.com"));
        users.add(new E_User("bbb","222","asdf","aaaa@naver.com"));
        users.add(new E_User("ccc","333","zxcv","aaaa@naver.com"));
        users.add(new E_User("ddd","444","tyui","aaaa@naver.com"));
        users.add(new E_User("eee","555","ghjk","aaaa@naver.com"));
        config.getServletContext().setAttribute("users", users);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("searchValue");
        ServletContext servletContext = request.getServletContext();
        List<E_User> users = (List<E_User>) servletContext.getAttribute("users");

        if(searchValue != null) {
            if(searchValue.isBlank()) {
                request.setAttribute("users", users.stream().
                        filter(u -> u.get()));
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        List<String> datas = List.of(username, password, name, eamil);
        for(String data : datas) {
            if(data == null) {
                response.getWriter().println("<script>alert()</script>");
            }
            if(data.isBlank()) {

            }
        }
    }
}
