package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.G_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class G_AuthDao {

    private DBConnectionMgr dbConnectionMgr;
    private static G_AuthDao instance;

    private G_AuthDao() {
        dbConnectionMgr = DBConnectionMgr.getInstance();
    }

    public static G_AuthDao getInstance() {
        if (instance == null) {
            instance = new G_AuthDao();
        }
        return instance;
    }

    public G_User findUserByUsername(String username) {
        G_User foundUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dbConnectionMgr.getConnection();
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
                        username = ?
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                foundUser = G_User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionMgr.freeConnection(con, ps, rs);
        }

        return foundUser;
    }

    public G_User signup(G_User user) {
        G_User insertedUser = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dbConnectionMgr.getConnection();
            String sql = "insert into user_tb values(default, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedUser = G_User.builder()
                        .userId(rs.getInt(1))
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionMgr.freeConnection(con, ps);
        }


        return insertedUser;
    }
}