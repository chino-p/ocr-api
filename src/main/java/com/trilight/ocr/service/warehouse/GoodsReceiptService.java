package com.trilight.ocr.service.warehouse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.model.dto.warehouse.GoodsReceiptDTO;
import com.trilight.ocr.model.dto.warehouse.GoodsReceiptDetailDTO;
import com.trilight.ocr.model.pojo.warehouse.GoodsReceiptDO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsReceiptService extends IService<GoodsReceiptDO>  {
    R<Void> uploadDeliverySheet(MultipartFile[] files);

    IPage<GoodsReceiptDTO> pageGoodsReceipt(PageQuery pageQuery);

    void downloadDeliverySheet(Long id, HttpServletResponse response);

    List<GoodsReceiptDetailDTO> getGoodsReceiptDetail(Long id);

    GoodsReceiptDTO searchErp(String docTypeNo, String docNo);
}
