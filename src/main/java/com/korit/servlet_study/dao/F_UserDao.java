package com.korit.servlet_study.dao;


import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.E_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class F_UserDao {

    private static F_UserDao userDao = null;

    private F_UserDao() {}

    public static F_UserDao getInstance() {
        if(userDao == null) {
            userDao = new F_UserDao();
        }
        return userDao;
    }

    public List<E_User> findAllBySearchValue(String searchValue) {
        List<E_User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                select
                    user_id,
                    username,
                    password,
                    name,
                    email
                from
                    user_tb
                where
                    username like concat('%', ?, '%')                    
            """;
            ps = con.prepareStatement(sql);
            ps.setString(1, searchValue);
            rs = ps.executeQuery();

            while(rs.next()) {
                users.add(E_User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public List<E_User> findAll() {
        List<E_User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection(); //db접속
            String sql = """
                select
                    user_id,
                    username,
                    password,
                    name,
                    email
                from
                    user_tb
            """;
            //select 입력시 *는 가능한 쓰지말기
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); //db 실행

            while(rs.next()) { //열에 값이 있으면 행을 돌면서 값 대입
                users.add(E_User.builder()
                                .userId(rs.getInt(1))
                                .username(rs.getString(2))
                                .password(rs.getString(3))
                                .name(rs.getString(4))
                                .email(rs.getString(5))
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }



    public Optional<E_User> save(E_User user) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                insert into user_tb 
                values(default, ?, ?, ?, ?)
            """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.executeUpdate(); //insert, update, delete
            //executeUpdate의 int는 성공횟수를 의미
            ResultSet keyRs = ps.getGeneratedKeys();
            keyRs.next(); //next는 n번쨰행, get은 n번쨰열
            int userId = keyRs.getInt(1); //값만들자마자 키값부여
            user.setUserId(userId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps);
        }

        return Optional.ofNullable(user);
    }
}
