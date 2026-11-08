package com.wilton.mobiauto_backend_interview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "video")
@Table(name = "videos")
public class Video {
    
    private String title;

    private Long id;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
