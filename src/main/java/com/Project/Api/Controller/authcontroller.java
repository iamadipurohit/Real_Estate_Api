package com.Project.Api.Controller;


import com.Project.Api.Entity.User;
import com.Project.Api.Repo.ItemRepository;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class authcontroller {

//
//    @Autowired
//    private AuthenticationManager authenticationManager;

    ItemRepository itemrepo;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public authcontroller(ItemRepository itemrepo) {
        this.itemrepo = itemrepo;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    @PostMapping("/signup")
    @Transactional
    @Async
    public ResponseEntity<Object> SignUp(@Valid  @RequestBody User user) {

        Optional<User> existingUser = itemrepo.findByUsername(user.getUsername());
        Optional<User> existingPass = itemrepo.findByEmail(user.getEmail());
        System.out.println(existingUser+" "+existingPass);
        if (existingUser.isPresent() || existingPass.isPresent()) {
            // If a user with the same username exists, throw an exception
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         itemrepo.insert(user);
        return ResponseEntity.ok().body(Map.of("success", true, "message", "User created successfully","userimg",user.getPhoto(),"username",user.getUsername(),"id",user.getId(),"email",user.getEmail()));
    }

    @Transactional
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {
        Optional<User> existingUserOptional = itemrepo.findByUsername(user.getUsername());
        if (existingUserOptional.isPresent()) {
            System.out.println(existingUserOptional.get());

            User existingUser = existingUserOptional.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
                System.out.println(user);
                System.out.println(user.getEmail());
                return ResponseEntity.ok().body(Map.of("success", true, "message", "User logged In","userimg",existingUser.getPhoto(),"username",existingUser.getUsername(),"id",existingUser.getId(),"email",existingUser.getEmail()));
            } else {
                throw new RuntimeException("Username or pass wrong");
            }
        } else {
            throw new RuntimeException("Username or pass wrong");
        }
    }
    @Transactional
    @PostMapping("/google")
    public ResponseEntity<?> googleauthentication(@Valid @RequestBody User user) {
        Optional<User> existingUserOptional = itemrepo.findByUsername(user.getUsername());
        try {
            if (!existingUserOptional.isPresent()) {
                String password="ejkfjkk";

                user.setPassword(passwordEncoder.encode(password));

                itemrepo.insert(user);
                Optional<User> user1=itemrepo.findByUsername(user.getUsername());
                User user2= user1.get();
                return ResponseEntity.ok().body(Map.of("success", true, "message", "User Created","id",user2.getId(),"userimg",user2.getPhoto(),"username",user2.getUsername(),"email",user2.getEmail()));
            }
            else {
                User user1= existingUserOptional.get();
                System.out.println("heelo user h");
                return ResponseEntity.ok().body(Map.of("success", true, "message", "Logged In","id",user1.getId(),"userimg",user1.getPhoto()!=null?user1.getPhoto():null,"username",user1.getUsername()!=null?user1.getUsername():null,"email",user1.getEmail()!=null?user1.getEmail():null));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,  @RequestBody User user) {
        try {
            Optional<User> existingUserOptional = itemrepo.findById(id);
            if (!existingUserOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "User not found"));
            }
            User existingUser = existingUserOptional.get();

            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            // Save the updated user
            itemrepo.save(existingUser);

            return ResponseEntity.ok().body(Map.of("success", true, "message", "User updated", "userimg",existingUser.getPhoto(),"username",existingUser.getUsername(),"id",existingUser.getId(),"email",existingUser.getEmail()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @DeleteMapping("/delete/{id}")

    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        try {
            Optional<User> existingUserOptional = itemrepo.findById(id);
            itemrepo.findBy({id= "aewfer"})
            if (!existingUserOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "User not found"));
            }
            User existingUser = existingUserOptional.get();

            itemrepo.delete(existingUser);

            return ResponseEntity.ok().body(Map.of("success", true, "message", "User Deleted"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
