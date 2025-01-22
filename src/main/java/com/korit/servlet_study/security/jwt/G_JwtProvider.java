package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.G_User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class G_JwtProvider {
    private static G_JwtProvider instance;
    private Key key;

    //암호 받기
    private G_JwtProvider() {
        final String SECRET = "3234b64f747b861ad99d9e62d9153b96c567e26d5579d04a6b63faca27cf7b6adfcaba7ef50f1b1233e833232c23893be31dca2e26c4b0dd5a7d497e870662935cdade3b5dac189b2552d87040cbdf568865e6b21764ca39f6af555221660b107e0f9456ef2f13506be5b1de257898e96ce4b1bd72464f1ceabd35d6a5ff08c83ca27c37f3fb3ce85ed2526c5a8869567e40478cd19e875983328c97a88da767c26fd70de7988b94a16fc1f5ebc393e6b5cddef7148beef0088b65cae9ef769c7d38e6d8b8da246211253aed4495a0e220de517f17e2a3a4d15f473f86671d9b5eb5f6fc1dfe74a633ff734f774d743ed3739ff73065c0fedc6b71c12c3ac2c8";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public static G_JwtProvider getInstance() {
        if(instance == null) {
            instance = new G_JwtProvider();
        }
        return instance;
    }

    private Date getExpiredate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365));//롱타입으로 기입
    }
    //토큰 생성
    public String generateToken(G_User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId()) //토큰 설정
                .setExpiration(getExpiredate()) //유효기간 설정
                .signWith(key, SignatureAlgorithm.HS256) //암호 종류
                .compact(); //토큰 생성
    }
}
