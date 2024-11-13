package com.trilight.ocr.service.purchase;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.model.dto.purchase.DeliveryOrderDTO;
import com.trilight.ocr.model.pojo.DeliveryOrderDO;
import org.springframework.web.multipart.MultipartFile;

@DS("tds")
public interface DeliveryOrderService extends IService<DeliveryOrderDO> {
    PageResult<DeliveryOrderDTO> pageDeliveryOrder(PageQuery pageQuery, DeliveryOrderDTO deliveryOrderDTO);

    void parseCode(MultipartFile[] files);
}
