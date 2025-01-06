package com.korit.servlet_study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")  //주소설정
public class A_Hello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //doGet으로 불러오기
        req.getRequestDispatcher("/WEB-INF/a_hello.jsp").forward(req, resp);
        //해당 명령어로 접근
    }
}