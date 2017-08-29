package com.hennen.moviechooser.models.data;

import com.hennen.moviechooser.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mark on 8/29/2017.
 */
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
