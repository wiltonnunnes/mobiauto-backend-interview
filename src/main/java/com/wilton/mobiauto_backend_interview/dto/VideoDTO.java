package com.wilton.mobiauto_backend_interview.dto;

public class VideoDTO {

    private String id;

    private String title;

    private UserDTO user;

    public VideoDTO() {}

    public VideoDTO(String id, String title, UserDTO user) {
        this.id = id;
        this.title = title;
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
