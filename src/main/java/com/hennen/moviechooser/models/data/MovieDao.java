package com.hennen.moviechooser.models.data;

import com.hennen.moviechooser.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mark on 5/17/2017.
 */
@Repository
@Transactional
public interface MovieDao extends CrudRepository<Movie, Integer> {
}
