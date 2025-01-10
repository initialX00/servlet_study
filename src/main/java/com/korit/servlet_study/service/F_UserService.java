package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.F_UserDao;
import com.korit.servlet_study.entity.E_User;

import java.util.List;
import java.util.Optional;

public class F_UserService {

    private F_UserDao userDao;
    private static F_UserService userService = null;

    private F_UserService() {
        userDao = F_UserDao.getInstance();
    }

    public static F_UserService getInstance() {
        if (userService == null) {
            userService = new F_UserService();
        }
        return userService;
    }

    public List<E_User> getAllUsers(String searchValue) {
        if(searchValue == null || searchValue.isBlank()) {
            return userDao.findAll();
        }
        return userDao.findAllBySearchValue(searchValue);
    }

    public E_User addUser(E_User user) {
        Optional<E_User> userOptional = userDao.save(user);
        return userOptional.get();
    }
}
