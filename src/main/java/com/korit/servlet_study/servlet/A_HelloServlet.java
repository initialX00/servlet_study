package com.korit.servlet_study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/helloservlet")  //주소설정
public class A_HelloServlet extends HttpServlet {

    String name = "aaa";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("name"));

        resp.getWriter().println("<html>" +
                "<head>" +
                "<title>Hello</title>" +
                "</head>" +
                "<body>" +
                "<h1>Hello Servlet" + name + "</h1>" +
                "</body>" +
                "</html>");


        req.getRequestDispatcher("/WEB-INF/a_hello.jsp").forward(req, resp);
    }
}
