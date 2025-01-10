package com.draw.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.draw.entity.LoginLog;
import com.draw.entity.WxUser;
import com.draw.mapper.LoginLogMapper;
import com.draw.mapper.WxUserMapper;
import com.draw.service.WxUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class WxUserServiceImpl implements WxUserService {
    private final WxUserMapper wxUserMapper;
    private final LoginLogMapper loginLogMapper;
    
    @Override
    @Transactional
    public WxUser login(String code, HttpServletRequest request) {
        try {
            // 测试环境，使用模拟的 openId
            String openId = "test_openid_" + System.currentTimeMillis();
            
            // 查找或创建用户
            WxUser user = wxUserMapper.findByOpenId(openId);
            if (user == null) {
                user = new WxUser();
                user.setOpenId(openId);
                user.setNickName("用户" + openId.substring(openId.length() - 6));
                wxUserMapper.insert(user);
            }
            
            // 记录登录日志
            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(user.getId());
            loginLog.setIpAddress(getIpAddress(request));
            loginLog.setDeviceInfo(request.getHeader("User-Agent"));
            loginLogMapper.insert(loginLog);
            
            return user;
        } catch (Exception e) {
            throw new RuntimeException("登录失败", e);
        }
    }
    
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
} 