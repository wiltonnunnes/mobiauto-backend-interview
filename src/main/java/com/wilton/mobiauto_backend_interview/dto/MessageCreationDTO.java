package com.wilton.mobiauto_backend_interview.dto;

import java.time.LocalDateTime;

public class MessageCreationDTO {
    
    private Long receiverId;

    private String content;

    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
    
}
