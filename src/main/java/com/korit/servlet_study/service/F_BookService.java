package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.F_BookDao;
import com.korit.servlet_study.entity.F_Book;

public class F_BookService {
    private F_BookDao bookDao;
    private static F_BookService bookService;

    private F_BookService() {
        bookDao = F_BookDao.getInstance();
    }

    public static F_BookService getInstance() {
        if (bookService == null) {
            bookService = new F_BookService();
        }
        return bookService;
    }

    public F_Book addBook(F_Book book) {
        bookDao.saveAuthor(book.getAuthor());
        bookDao.savePublisher(book.getPublisher());
        bookDao.saveBookCategory(book.getCategory());
        return bookDao.saveBook(book).get();
    }
}
