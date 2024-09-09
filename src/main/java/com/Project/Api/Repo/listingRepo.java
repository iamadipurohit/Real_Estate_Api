package com.Project.Api.Repo;

import com.Project.Api.Entity.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface listingRepo extends MongoRepository<Listing, String> {

    Optional<Listing> findByname(String name);

    List<Listing> findByuserRef(String id);

    List<Listing> findAllByuserRef(String id);


//    Optional<Listing> findByListingName(String name);
}