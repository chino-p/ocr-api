package com.trilight.ocr.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.system.UserMapper;
import com.trilight.ocr.model.pojo.system.UserDO;
import com.trilight.ocr.service.system.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements
        UserService {

}
