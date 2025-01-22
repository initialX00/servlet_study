package com.korit.servlet_study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class G_ResponseDto<T> {
    private int status;
    private String message;
    private T data;

    public static <T> G_ResponseDto<T> success(T data) {
        return new G_ResponseDto<>(200, "success", data);
    }

    public static <T> G_ResponseDto<T> fail(T data) {
        return new G_ResponseDto<>(400, "fail", data);
    }
}