package com.hennen.moviechooser.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 5/30/2017.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 3, max = 15)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Movie> movies = new ArrayList<>();

    //private String verify = "";

    public User() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

//    public String getVerify() {
//        return verify;
//    }
//
//    public void setVerify(String verify) {
//        this.verify = verify;
//    }
}
