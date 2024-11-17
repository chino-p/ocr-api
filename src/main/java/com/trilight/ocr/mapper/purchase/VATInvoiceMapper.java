package com.trilight.ocr.mapper.purchase;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.purchase.VATInvoiceDO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface VATInvoiceMapper extends BaseMapper<VATInvoiceDO> {

    IPage<VATInvoiceDTO> pageVATInvoice(IPage<VATInvoiceDTO> page, @Param("vatInvoice") VATInvoiceDTO vatInvoiceDTO, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("createStartTime") LocalDateTime createStartTime, @Param("createEndTime") LocalDateTime createEndTime);
}
