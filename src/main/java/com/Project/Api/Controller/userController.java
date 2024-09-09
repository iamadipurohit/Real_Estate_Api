//package com.Project.Api.Controller;
//
//import com.Project.Api.Entity.User;
//import com.Project.Api.Repo.ItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//public class userController {
//
//    private ItemRepository itemRepository;
//
//    @Autowired
//    public userController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
//    @GetMapping("/user/{id}")
//    private ResponseEntity<Map<String, Object>> getuser(@PathVariable("id") String id)
//    {
//        Optional<User> temp=itemRepository.findById(id);
//        if(temp.isPresent())
//        {
//            User u=temp.get();
//            Map<String, Object> userDetails = new HashMap<>();
//            userDetails.put("id", u.getId());
//            userDetails.put("username", u.getUsername());
//            userDetails.put("email", u.getEmail());
//            userDetails.put("otherDetails", u.getPhoto());
//
//            // Create a response entity with the map of user details
//            ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.ok(userDetails);
//
//            // Now you can return this response entity as a response
//            return responseEntity;
//        }
//        throw new RuntimeException("No User");
//    }
//}
