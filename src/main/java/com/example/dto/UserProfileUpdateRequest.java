package com.example.dto;

public class UserProfileUpdateRequest {
    private String username;
    private String email;
    private String avatar;
    private String bio;

    // Default constructor
    public UserProfileUpdateRequest() {}

    // Constructor with all fields
    public UserProfileUpdateRequest(String username, String email, String avatar, String bio) {
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.bio = bio;
    }

    // Getters and Setters
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}