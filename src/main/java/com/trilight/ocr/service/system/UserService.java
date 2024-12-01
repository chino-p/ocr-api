package com.trilight.ocr.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.system.SysUserDTO;
import com.trilight.ocr.model.pojo.system.SysUserDO;

public interface UserService extends IService<SysUserDO> {
    R<String> login(SysUserDTO userDTO);
}
