package com.draw.service;

import com.draw.entity.WxUser;
import jakarta.servlet.http.HttpServletRequest;

public interface WxUserService {
    WxUser login(String code, HttpServletRequest request);
} 