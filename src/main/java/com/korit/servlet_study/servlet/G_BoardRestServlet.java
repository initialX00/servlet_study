package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.dto.G_InsertBoardDto;
import com.korit.servlet_study.service.G_BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/api/board")
public class G_BoardRestServlet extends HttpServlet {
    private G_BoardService boardService;

    public G_BoardRestServlet() {
        boardService = G_BoardService.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //문자열을 합치는 StringBuilder(싱글스레드),StringBuffer(멀티스레드)
        StringBuilder stringBuilder = new StringBuilder();

        //axios는 post요청때 자동으로 json으로 변환해준다. 따라서 json을 변환해줘야한다
        try (BufferedReader bufferedReader = request.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                //System.out.println(line);
                //일정 단위로 한줄씩 끊어서 문자열을 읽는다. 해당 라인이 null이 아니면 출력한다.
            }
        }

        //json을 java객체로 전환
        ObjectMapper objectMapper = new ObjectMapper();
        G_InsertBoardDto insertBoardDto = objectMapper.readValue(stringBuilder.toString(), G_InsertBoardDto.class);
        System.out.println(insertBoardDto);

        boardService.insertBoard(insertBoardDto);

        //클라이언트 <-제이슨-> 톰캣 <-> 서블릿 <-디티오-> 서비스 <-엔티티-> 디에이오

    }
}
