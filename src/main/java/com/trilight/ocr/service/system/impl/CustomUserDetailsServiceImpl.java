package com.trilight.ocr.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.model.pojo.system.SysUserDO;
import com.trilight.ocr.service.system.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO userDO = userService.getOne(new QueryWrapper<SysUserDO>().eq("username", username));
        if (userDO == null) {
            throw new BizException(BizCodeEnum.USER_NOT_FOUND);
        }
        return new UserPrinciple(userDO);
    }
}
