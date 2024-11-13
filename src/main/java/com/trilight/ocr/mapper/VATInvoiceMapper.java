package com.trilight.ocr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.VATInvoiceDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VATInvoiceMapper extends BaseMapper<VATInvoiceDO> {

    IPage<VATInvoiceDTO> pageVATInvoice(IPage<VATInvoiceDTO> page, @Param("vatInvoice") VATInvoiceDTO vatInvoiceDTO, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
