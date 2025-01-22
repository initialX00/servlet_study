package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.G_AuthDao;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.dto.G_SigninDto;
import com.korit.servlet_study.dto.G_SignupDto;
import com.korit.servlet_study.entity.G_User;
import com.korit.servlet_study.security.jwt.G_JwtProvider;
import org.mindrot.jbcrypt.BCrypt;

public class G_AuthService {
    private G_AuthDao authDao;
    private G_JwtProvider jwtProvider;

    private static G_AuthService instance;

    private G_AuthService() {
        authDao = G_AuthDao.getInstance();
        jwtProvider = G_JwtProvider.getInstance();
    }

    public static G_AuthService getInstance() {
        if (instance == null) {
            instance = new G_AuthService();
        }
        return instance;
    }

    public G_ResponseDto<?> signup(G_SignupDto signupDto) {
        G_User insertedUser = authDao.signup(signupDto.toUser());
        if(insertedUser == null) {
            return G_ResponseDto.fail("사용자를 추가하지 못하였습니다.");
        }
        return G_ResponseDto.success(insertedUser);
    }

    public G_ResponseDto<?> signin(G_SigninDto signinDto) {
        G_User foundUser = authDao.findUserByUsername(signinDto.getUsername());
        if(foundUser == null) {
            return G_ResponseDto.fail("사용자 정보를 다시 확인하세요.");
        }
        if(!BCrypt.checkpw(signinDto.getPassword(), foundUser.getPassword())) {
            return G_ResponseDto.fail("사용자 정보를 다시 확인하세요.");
        }
        return G_ResponseDto.success(jwtProvider.generateToken(foundUser));
    }
}