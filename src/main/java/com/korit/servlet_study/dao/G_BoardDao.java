package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.G_Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class G_BoardDao {
    private DBConnectionMgr dbConnectionMgr;
    private static G_BoardDao instance;

    private G_BoardDao() {
        dbConnectionMgr = DBConnectionMgr.getInstance();
    }

    public static G_BoardDao getInstance() {
        if (instance == null) {
            instance = new G_BoardDao();
        }
        return instance;
    }


    public G_Board save(G_Board board) {
        G_Board insertedBoard = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dbConnectionMgr.getConnection();
            String sql = """
                    insert into board_tb
                    values(default, ?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedBoard = G_Board.builder()
                        .boardId(rs.getInt(1))
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbConnectionMgr.freeConnection(con, ps);
        }

        return insertedBoard;
    }
}