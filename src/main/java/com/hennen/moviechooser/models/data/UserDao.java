package com.hennen.moviechooser.models.data;

import com.hennen.moviechooser.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mark on 5/30/2017.
 */
@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
}
