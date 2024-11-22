package com.trilight.ocr.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.system.UserMapper;
import com.trilight.ocr.model.dto.system.SysUserDTO;
import com.trilight.ocr.model.pojo.system.SysUserDO;
import com.trilight.ocr.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUserDO> implements
        UserService {
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(SysUserDTO userDTO) {
        SysUserDO sysUserDO = getOne(new QueryWrapper<SysUserDO>().eq("username", userDTO.getUsername()));
        if (sysUserDO == null) {
            return "fail";
        }

        if(!passwordEncoder.matches(userDTO.getPassword(), sysUserDO.getPassword())) {
            return "fail";
        }
        return jwtService.generateToken(userDTO.getUsername());
    }
}
