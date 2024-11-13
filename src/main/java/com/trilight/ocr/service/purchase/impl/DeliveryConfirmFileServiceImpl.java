package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.DeliveryConfirmFileMapper;
import com.trilight.ocr.model.pojo.DeliveryConfirmFileDO;
import com.trilight.ocr.service.purchase.DeliveryConfirmFileService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryConfirmFileServiceImpl extends ServiceImpl<DeliveryConfirmFileMapper, DeliveryConfirmFileDO>
        implements
        DeliveryConfirmFileService {
}
