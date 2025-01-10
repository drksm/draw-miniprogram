package com.draw.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WxUser {
    private Long id;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 