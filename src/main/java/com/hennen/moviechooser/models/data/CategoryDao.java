package com.hennen.moviechooser.models.data;

import com.hennen.moviechooser.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mark on 5/24/2017.
 */
@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
