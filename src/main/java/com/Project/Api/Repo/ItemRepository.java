package com.Project.Api.Repo;

import com.Project.Api.Entity.Listing;
import com.Project.Api.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ItemRepository extends MongoRepository<User,String> {

    public long count();

    Optional<User> findByUsername(String username);

//    Optional<User> findByPassword(String username);

    Optional<User> findByEmail(String email);


}
