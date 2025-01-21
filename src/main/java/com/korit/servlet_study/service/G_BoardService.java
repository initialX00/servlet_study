package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.G_BoardDao;
import com.korit.servlet_study.dto.G_InsertBoardDto;
import com.korit.servlet_study.entity.G_Board;

public class G_BoardService {

    private G_BoardDao boardDao;
    private static G_BoardService instance;

    private G_BoardService() {
        boardDao = G_BoardDao.getInstance();
    }

    public static G_BoardService getInstance() {
        if (instance == null) {
            instance = new G_BoardService();
        }
        return instance;
    }


    public void insertBoard(G_InsertBoardDto dto) {
        G_Board board = dto.toBoard();
        boardDao.save(board);
    }
}
