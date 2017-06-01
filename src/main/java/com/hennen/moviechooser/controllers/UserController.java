package com.hennen.moviechooser.controllers;

import com.hennen.moviechooser.models.User;
import com.hennen.moviechooser.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mark on 5/30/2017.
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "User Login");
        model.addAttribute(new User());

        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAdd(Model model) {

        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute @Valid User newUser,
                             Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "User Signup");
            return "user/add";
        }

        userDao.save(newUser);
        return "redirect:";
    }

}
