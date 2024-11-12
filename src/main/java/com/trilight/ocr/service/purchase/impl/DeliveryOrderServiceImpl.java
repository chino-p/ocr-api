package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.mapper.DeliveryOrderMapper;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.model.pojo.DeliveryOrderDO;
import com.trilight.ocr.service.purchase.DeliveryOrderService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderServiceImpl extends ServiceImpl<DeliveryOrderMapper, DeliveryOrderDO> implements
        DeliveryOrderService {

    @Override
    public PageResult<DeliveryOrderDTO> pageDeliveryOrder(PageQuery pageQuery, DeliveryOrderDTO deliveryOrderDTO) {
        return PageResult.build(baseMapper.pageDeliveryOrder(pageQuery.build(), deliveryOrderDTO));
    }
}
