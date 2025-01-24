package com.korit.servlet_study.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) //어노테이션 사용할 위치
@Retention(RetentionPolicy.RUNTIME) //어노테이션 사용할 시점(런타임,테스트)
public @interface G_JwtValid {
}
