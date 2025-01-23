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
    private T body;

    public static <T> G_ResponseDto<T> success(T body) {
        return new G_ResponseDto<>(200, "success", body);
    }

    public static <T> G_ResponseDto<T> fail(T body) {
        return new G_ResponseDto<>(400, "fail", body);
    }

    public static <T> G_ResponseDto<T> forbidden(T body) {
        return new G_ResponseDto<>(403, "Forbidden", body);
    }
}