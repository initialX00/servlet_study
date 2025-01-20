package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.F_Author;
import com.korit.servlet_study.entity.F_Book;
import com.korit.servlet_study.entity.F_Category;
import com.korit.servlet_study.entity.F_Publisher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class G_BookRestServlet extends HttpServlet {

    //http get요청을 처리하는 메서드
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        F_Author author = new F_Author(1, "테스트저자");
        F_Publisher publisher = new F_Publisher(10, "테스트출판사");
        F_Category category = new F_Category(100, "테스트카테고리");

        F_Book book = new F_Book().builder()
                .bookId(200)
                .bookName("테스트도서명")
                .isbn("test1234")
                .bookImgUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/1200px-React-icon.svg.png")
                .authorId(author.getAuthorId())
                .publisherId(publisher.getPublisherId())
                .categoryId(category.getCategoryId())
                .author(author)
                .publisher(publisher)
                .category(category)
                .build();

        //객체를 json으로 변환
        ObjectMapper mapper = new ObjectMapper(); //java객체를 json문자열로 변환
        String json = mapper.writeValueAsString(book); //json형식을 문자열로 변환

        //서버응답 세팅을 필터를 통해 한다.

        resp.setContentType("application/json");
        resp.getWriter().println(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
