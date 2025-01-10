package com.draw.mapper;

import com.draw.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {
    void insert(LoginLog loginLog);
} 