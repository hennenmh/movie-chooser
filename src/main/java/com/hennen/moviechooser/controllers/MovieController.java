package com.hennen.moviechooser.controllers;

import com.hennen.moviechooser.models.data.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mark on 5/17/2017.
 */

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Movie Chooser");

        return "movie/index";
    }

}
