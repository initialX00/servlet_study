package com.korit.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class G_Book {
    private int bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String category;
    private String imgUrl;
}
