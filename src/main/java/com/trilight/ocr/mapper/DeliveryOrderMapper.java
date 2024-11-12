package com.trilight.ocr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.model.pojo.DeliveryOrderDO;

public interface DeliveryOrderMapper extends BaseMapper<DeliveryOrderDO> {

    IPage<DeliveryOrderDTO> pageDeliveryOrder(IPage<DeliveryOrderDTO> page, DeliveryOrderDTO deliveryOrderDTO);
}
