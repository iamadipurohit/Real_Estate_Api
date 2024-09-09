package com.Project.Api.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;

@Document(collection = "User")
public class User {


    @Id
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank
    @Indexed(unique = true)
//    @Id
    private String username;

//    @NotBlank
    @Indexed(unique = true)
    private String email;

    @Size(max = 20)
    private String password;

    private String photo="https://core.luminousindia.com/luminousweb/Image/171-1717870_stockvader-predicted-cron-for-may-user-profile-icon-png.png";
    public User() {
    }

    public User(  String username,String email,String password) {
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
