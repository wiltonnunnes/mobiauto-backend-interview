package com.wilton.mobiauto_backend_interview.dto;

public class VideoDTO {

    private String videoId;

    private String title;

    private UserDTO user;

    public VideoDTO() {}

    public VideoDTO(String videoId, String title, UserDTO user) {
        this.videoId = videoId;
        this.title = title;
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
