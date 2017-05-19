package com.hennen.moviechooser.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Mark on 5/17/2017.
 */

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private int id;

    public Movie(){}

}
