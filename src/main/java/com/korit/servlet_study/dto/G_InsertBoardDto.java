package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.G_Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class G_InsertBoardDto {
    private String title;
    private String content;

    public G_Board toBoard() {
        return G_Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
