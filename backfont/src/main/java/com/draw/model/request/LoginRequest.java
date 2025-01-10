package com.draw.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String code;
    private String nickName;
    private String avatarUrl;
} 