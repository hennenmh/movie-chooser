package com.hennen.moviechooser.controllers;

import com.hennen.moviechooser.models.User;
import com.hennen.moviechooser.models.data.CategoryDao;
import com.hennen.moviechooser.models.data.MovieDao;
import com.hennen.moviechooser.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Mark on 8/29/2017.
 */
public abstract class AbstractController {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected MovieDao movieDao;

    @Autowired
    protected CategoryDao categoryDao;

    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findOne(userId);
    }
    protected void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getUid());
    }

    @ModelAttribute("user")
    public User getUserForModel(HttpServletRequest request) {
        return getUserFromSession(request.getSession());
    }
}
