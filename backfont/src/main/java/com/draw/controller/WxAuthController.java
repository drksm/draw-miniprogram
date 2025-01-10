package com.draw.controller;

import com.draw.common.R;
import com.draw.entity.WxUser;
import com.draw.model.request.LoginRequest;
import com.draw.service.WxUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/wx")
@RequiredArgsConstructor
public class WxAuthController {
    private static final Logger log = LoggerFactory.getLogger(WxAuthController.class);
    private final WxUserService wxUserService;
    
    @PostMapping("/login")
    public R<WxUser> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        try {
            log.info("收到登录请求: {}", request);
            if (request == null || request.getCode() == null) {
                return R.error("参数错误");
            }
            
            WxUser user = wxUserService.login(request.getCode(), httpRequest);
            return R.success(user);
        } catch (Exception e) {
            log.error("登录失败", e);
            return R.error(e.getMessage());
        }
    }
} 