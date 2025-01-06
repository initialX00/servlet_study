package com.korit.servlet_study.datas;

import com.korit.servlet_study.entity.B_Category;

import java.util.List;

public class B_DataList {
    public static List<B_Category> getCategoryList() {
        return List.of(
                new B_Category(1, "커피"),
                new B_Category(2, "에이드"),
                new B_Category(3, "티")
        );
    }
}
