package com.Project.Api.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Listing")
public class Listing {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String address;
    @NotBlank
    private int regularPrice;
    @NotBlank
    private int discountPrice;
    @NotBlank
    private String bathrooms;
    @NotBlank
    private String bedrooms;
    @NotBlank
    private boolean furnished;
    @NotBlank
    private boolean parking;
    @NotBlank
    private String type;
    @NotBlank
    private boolean offer;
    @NotBlank
    private ArrayList<String> imageUrls;
    @NotBlank
    private String userRef;

    public Listing() {
    }

    public Listing(String name, String description, String address, int regularPrice, int discountPrice, String bathrooms, String bedrooms, boolean furnished, boolean parking, String type, boolean offer, ArrayList<String> imageUrls, String userRef) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.furnished = furnished;
        this.parking = parking;
        this.type = type;
        this.offer = offer;
        this.imageUrls = imageUrls;
        this.userRef = userRef;
    }

    public Listing(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(int regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOffer() {
        return offer;
    }

    public void setOffer(boolean offer) {
        this.offer = offer;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", regularPrice=" + regularPrice +
                ", discountPrice=" + discountPrice +
                ", bathrooms='" + bathrooms + '\'' +
                ", bedrooms='" + bedrooms + '\'' +
                ", furnished=" + furnished +
                ", parking=" + parking +
                ", type='" + type + '\'' +
                ", offer=" + offer +
                ", imageUrls=" + imageUrls +
                ", userRef='" + userRef + '\'' +
                '}';
    }
}
