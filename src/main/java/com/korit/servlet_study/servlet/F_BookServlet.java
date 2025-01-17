package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.F_Author;
import com.korit.servlet_study.entity.F_Book;
import com.korit.servlet_study.entity.F_Category;
import com.korit.servlet_study.entity.F_Publisher;
import com.korit.servlet_study.service.F_BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book1")
public class F_BookServlet extends HttpServlet {
    private F_BookService bookService;

    public F_BookServlet() {
        bookService = F_BookService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/f_book_study1.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String isbn = req.getParameter("isbn");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String category = req.getParameter("category");
        String imgUrl = req.getParameter("imgurl");

        F_Author authorObj = new F_Author(0, author);
        F_Publisher publisherObj = new F_Publisher(0, publisher);
        F_Category categoryObj = new F_Category(0, category);
        F_Book book = F_Book.builder()
                .bookName(bookName)
                .isbn(isbn)
                .author(authorObj)
                .publisher(publisherObj)
                .category(categoryObj)
                .bookImgUrl(imgUrl)
                .build();


        bookService.addBook(book);
    }
}
