package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.purchase.DeliveryOrderDetailMapper;
import com.trilight.ocr.model.pojo.purchase.DeliveryOrderDetailDO;
import com.trilight.ocr.service.purchase.DeliveryOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderDetailServiceImpl extends ServiceImpl<DeliveryOrderDetailMapper, DeliveryOrderDetailDO> implements
        DeliveryOrderDetailService {
}
