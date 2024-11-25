package com.trilight.ocr.service.warehouse;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.pojo.warehouse.GoodsReceiptDO;
import org.springframework.web.multipart.MultipartFile;

public interface GoodsReceiptService extends IService<GoodsReceiptDO>  {
    R<Void> uploadDeliverySheet(MultipartFile[] files);

    void testCode(MultipartFile file);
}
