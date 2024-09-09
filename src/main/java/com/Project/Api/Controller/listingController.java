package com.Project.Api.Controller;

import com.Project.Api.Entity.Listing;
import com.Project.Api.Repo.listingRepo;
//import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@RequestMapping("/api/listing")
public class listingController {

    private listingRepo listingrepo;



    public listingController(listingRepo listingrepo) {
        this.listingrepo = listingrepo;

    }

    @PostMapping("/create")
    private Optional<Listing> listingfun(@RequestBody Listing listing)
    {
          listingrepo.insert(listing);
          return listingrepo.findByname(listing.getName());
    }
    @GetMapping("/userlistings/{userRef}")
    private List<Listing> givelistings(@PathVariable("userRef") String userRef)
    {
        List<Listing> list=listingrepo.findAllByuserRef(userRef);

       return list;
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deletelisting(@PathVariable("id") String id)
    {
        Optional<Listing> l=listingrepo.findById(id);
        if(l.isPresent())
        {
            Listing temp=l.get();
            listingrepo.delete(temp);

            return ResponseEntity.ok().body(Map.of("success", true, "message", "User Deleted"));

        }
         throw new RuntimeException("No listing");

    }
    @PostMapping("/update/{id}")
    private ResponseEntity<?> updatelisting(@PathVariable("id") String id,@RequestBody Listing listing) throws Exception {
        Optional<Listing> l=listingrepo.findById(id);
        if(l.isPresent())
        {
            Listing temp=l.get();
            listingrepo.save(temp);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("listing", temp); // Add the listing object itself
//            String json = objectMapper.writeValueAsString(responseMap);
            return ResponseEntity.ok().body(responseMap);

        }
        throw new RuntimeException("No listing");

    }
    @GetMapping("/get/{id}")
    private ResponseEntity<?> givelisting(@PathVariable("id") String id) throws Exception {
        Optional<Listing> list=listingrepo.findById(id);
        if(list.isPresent())
        {
            System.out.println("helllo");
            Listing temp = list.get();
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("listing", temp); // Add the listing object itself
//            String json = objectMapper.writeValueAsString(responseMap);
            return ResponseEntity.ok().body(responseMap);
//            return ResponseEntity.ok().body(Map.of("success", true, "message", "Bhej diya"));

        }
        else {
            System.out.println("ji");
            throw new RuntimeException("No listing found");
        }

    }
}
