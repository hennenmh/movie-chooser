package com.hennen.moviechooser.controllers;

import com.hennen.moviechooser.models.Category;
import com.hennen.moviechooser.models.Movie;
import com.hennen.moviechooser.models.User;
import com.hennen.moviechooser.models.data.CategoryDao;
import com.hennen.moviechooser.models.data.MovieDao;
import com.hennen.moviechooser.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by Mark on 5/17/2017.
 */

@Controller
@RequestMapping("movie")
public class MovieController extends AbstractController{

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session,
                        HttpServletRequest request) {

        model.addAttribute("title", "Movie Chooser");
        model.addAttribute("movies", movieDao.findAll());
        session.setAttribute("userId", getUserFromSession(request.getSession()).getUid());
        session.setAttribute("username", getUserFromSession(request.getSession()).getUsername());
        return "movie/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMovieForm(Model model) {

        model.addAttribute("title", "Add Movie");
        model.addAttribute(new Movie());
        model.addAttribute("categories", categoryDao.findAll());

        return "movie/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMovieForm(@ModelAttribute @Valid Movie newMovie,
                                      Errors errors, @RequestParam int categoryId,
                                      HttpServletRequest request,
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Movie");
            model.addAttribute("categories", categoryDao.findAll());
            return "movie/add";
        }
        
        Category cat = categoryDao.findOne(categoryId);
        User user = userDao.findOne(getUserFromSession(request.getSession()).getUid());
        newMovie.setCategory(cat);
        newMovie.setUser(user);
        movieDao.save(newMovie);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveMovieForm(Model model, HttpServletRequest request,
                                         HttpSession session) {

        model.addAttribute("movies", movieDao.findAll());
        model.addAttribute("title", "Remove Movie");
        session.setAttribute("userId", getUserFromSession(request.getSession()).getUid());
        return "movie/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveMovieForm(@RequestParam(required = false)int[] movieIds,
                                         Model model, HttpSession session,
                                         HttpServletRequest request) {

        try {
            for (int movieId : movieIds) {
                movieDao.delete(movieId);
            }
        } catch(Exception e) {
            model.addAttribute("movies", movieDao.findAll());
            model.addAttribute("title", "Remove Movie");
            session.setAttribute("userId", getUserFromSession(request.getSession()).getUid());
            model.addAttribute("message", "Please add or select movie(s) to delete");
            return "movie/remove";
        }


        return "redirect:";
    }

    @RequestMapping(value = "random", method = RequestMethod.GET)
    public String displayRandomMovieForm(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Movie Randomizer");
        return "movie/random";
    }

    @RequestMapping(value = "random", method = RequestMethod.POST)
    public String processRandomMovieForm(@RequestParam int categoryId,
                                         Model model, HttpServletRequest request) {

        Category cat = categoryDao.findOne(categoryId);
        User user = userDao.findOne(getUserFromSession(request.getSession()).getUid());
        List<Movie> catMovies = cat.getMovies();
        List<Movie> userMovies = user.getMovies();
        List<Movie> finalMovies = new ArrayList<Movie>(catMovies);
        finalMovies.retainAll(userMovies);

        try {
            String random = finalMovies.get(new Random().nextInt(finalMovies.size())).getName();
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Movie Randomizer");
            model.addAttribute("result", "You should watch " + random);
        } catch (Exception e) {
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Movie Randomizer");
            model.addAttribute("result", "No movies in that category");
            return "movie/random";
        }

        return "movie/random";
    }

}
