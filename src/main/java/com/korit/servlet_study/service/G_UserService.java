package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.G_UserDao;
import com.korit.servlet_study.dto.G_ResponseDto;
import com.korit.servlet_study.entity.G_User;

import java.util.List;
import java.util.Optional;

public class G_UserService {

    private G_UserDao userDao;
    private static G_UserService userService = null;

    private G_UserService() {
        userDao = G_UserDao.getInstance();
    }

    public static G_UserService getInstance() {
        if (userService == null) {
            userService = new G_UserService();
        }
        return userService;
    }

    public G_ResponseDto<?> getUser(int userId) {
        G_User foundUser = userDao.findById(userId);
        if(foundUser == null) {
            return G_ResponseDto.fail("user not found");
        }
        return G_ResponseDto.success(foundUser);
    }

    public List<G_User> getAllUsers(String searchValue) {
        if(searchValue == null || searchValue.isBlank()) {
            return userDao.findAll();
        }
        return userDao.findAllBySearchValue(searchValue);
    }

    public G_User addUser(G_User user) {
        Optional<G_User> userOptional = userDao.save(user);
        return userOptional.get();
    }
}
