// package com.trilight.ocr.service.system.impl;
//
// import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
// import com.trilight.ocr.mapper.system.UserMapper;
// import com.trilight.ocr.model.dto.system.SysUserDTO;
// import com.trilight.ocr.model.pojo.system.SysUserDO;
// import com.trilight.ocr.service.system.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Service;
//
// @RequiredArgsConstructor
// @Service
// public class UserServiceImpl extends ServiceImpl<UserMapper, SysUserDO> implements
//         UserService {
//
//     private final AuthenticationManager authenticationManager;
//     private final JWTService jwtService;
//
//     @Override
//     public String login(SysUserDTO userDTO) {
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
//         if (authentication.isAuthenticated()) {
//             return jwtService.generateToken(userDTO.getUsername());
//         } else {
//             return "fail";
//         }
//     }
// }
