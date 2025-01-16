package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.F_Author;
import com.korit.servlet_study.entity.F_Book;
import com.korit.servlet_study.entity.F_Category;
import com.korit.servlet_study.entity.F_Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class F_BookDao {
    private DBConnectionMgr mgr;
    private static F_BookDao bookDao;

    private F_BookDao() {
        mgr = DBConnectionMgr.getInstance();
    }

    public static F_BookDao getInstance() {
        if (bookDao == null) {
            bookDao = new F_BookDao();
        }
        return bookDao;
    }


    public Optional<F_Author> saveAuthor(F_Author author) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into author values (default, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getAuthorName()); //?에 대입
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                author.setAuthorId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(author);
    }

    public Optional<F_Publisher> savePublisher(F_Publisher publisher) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into publisher values (default, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, publisher.getPublisherName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                publisher.setPublisherId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(publisher);
    }

    public Optional<F_Category> saveBookCategory(F_Category bookCategory) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into category_tb values (default, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookCategory.getCategoryName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bookCategory.setCategoryId(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(bookCategory);
    }

    public Optional<F_Book> saveBook(F_Book book) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = mgr.getConnection();
            String sql = """
                    insert into book_tb values (default, ?, ?, ?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getBookName());
            ps.setInt(2, book.getAuthor().getAuthorId());
            ps.setString(3, book.getIsbn());
            ps.setInt(4, book.getPublisher().getPublisherId());
            ps.setInt(5, book.getCategory().getCategoryId());
            ps.setString(6, book.getBookImgUrl());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(book);
    }


}
