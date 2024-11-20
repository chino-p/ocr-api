// package com.trilight.ocr.controller.system;
//
// import com.trilight.ocr.common.model.R;
// import com.trilight.ocr.model.dto.system.SysUserDTO;
// import com.trilight.ocr.service.system.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// @RequiredArgsConstructor
// @RequestMapping("/api/user")
// @RestController
// public class UserController {
//
//     private final UserService userService;
//
//     @PostMapping("/login")
//     public R<String> login(@RequestBody SysUserDTO userDTO) {
//         String token = userService.login(userDTO);
//         return R.ok("login success");
//     }
// }
