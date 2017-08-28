package com.hennen.moviechooser.controllers;

import com.hennen.moviechooser.models.Category;
import com.hennen.moviechooser.models.Movie;
import com.hennen.moviechooser.models.data.CategoryDao;
import com.hennen.moviechooser.models.data.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

/**
 * Created by Mark on 5/17/2017.
 */

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Movie Chooser");
        model.addAttribute("movies", movieDao.findAll());
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
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Movie");
            return "movie/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newMovie.setCategory(cat);
        movieDao.save(newMovie);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveMovieForm(Model model) {
        model.addAttribute("movies", movieDao.findAll());
        model.addAttribute("title", "Remove Movie");
        return "movie/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveMovieForm(@RequestParam int[] movieIds) {

        for (int movieId : movieIds) {
            movieDao.delete(movieId);
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
                                         Model model) {

        Category cat = categoryDao.findOne(categoryId);
        List<Movie> movies = cat.getMovies();
        String random = movies.get(new Random().nextInt(movies.size())).getName();
        //String random = movies.get(new Random().nextInt(movies.size()));
        model.addAttribute("result", random);

        return "redirect:";
    }

}
