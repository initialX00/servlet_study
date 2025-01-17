package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.E_User;
import com.korit.servlet_study.entity.F_Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class G_UserRestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        E_User user = E_User.builder()
                .username("test")
                .password("1234")
                .name("테스트")
                .email("test@mail")
                .build();

        String jsonUser = objectMapper.writeValueAsString(user);
        System.out.println(jsonUser);

        F_Book book = F_Book.builder() //G_Book으로 따로 생성해주기
                .bookId(111)
                .bookName("1234")
                .author()
                .publisher("test@mail")
                .category()
                .imgUrl()
                .build();

        String jsonBook = objectMapper.writeValueAsString(book);
        System.out.println(jsonBook);


        //서버응답 허용 세팅
        response.setHeader("Access-Control-Allow-Origin", "*"); //Origin이란 출처대상, 즉 서버를 의미한다
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");


        response.setContentType("application/json");
        response.getWriter().println(jsonUser);
        response.getWriter().println(jsonBook);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
