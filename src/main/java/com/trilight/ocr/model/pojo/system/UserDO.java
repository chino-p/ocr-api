package com.trilight.ocr.model.pojo.system;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserDO {

    @TableId
    private Long id;
    private String username;
    private String password;
    private String role;
}
