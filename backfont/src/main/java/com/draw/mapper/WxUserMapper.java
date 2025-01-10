package com.draw.mapper;

import com.draw.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WxUserMapper {
    WxUser findByOpenId(@Param("openId") String openId);
    void insert(WxUser user);
    void update(WxUser user);
} 