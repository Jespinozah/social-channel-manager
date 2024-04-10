package com.example.social.manager.repository;

import com.example.social.manager.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
