package com.trilight.ocr.service.purchase.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
import com.trilight.ocr.mapper.CommodityMapper;
import com.trilight.ocr.mapper.VATInvoiceMapper;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import com.trilight.ocr.model.pojo.CommodityDO;
import com.trilight.ocr.model.pojo.VATInvoiceDO;
import com.trilight.ocr.service.purchase.VATInvoiceService;
import com.trilight.ocr.utils.FileProcessUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VATInvoiceServiceImpl extends ServiceImpl<VATInvoiceMapper, VATInvoiceDO> implements VATInvoiceService  {

    private final CommodityMapper commodityMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public R<Void> uploadInvoiceFiles(MultipartFile[] files) {
        try {
            List<String> base64Contents = FileProcessUtil.processFile(files);

            List<VATInvoiceDTO> vatInvoiceDTOList = BaiduOcrClient.parseVatInvoice(base64Contents);

            List<VATInvoiceDO> vatInvoiceDOList = BeanUtil.copyToList(vatInvoiceDTOList, VATInvoiceDO.class);
            baseMapper.insert(vatInvoiceDOList);
            List<CommodityDO> commodityDOList = vatInvoiceDOList.stream()
                    .flatMap(vatInvoiceDO -> vatInvoiceDO.getCommodityList().stream().peek(commodityDO -> commodityDO.setVatInvoiceId(vatInvoiceDO.getVatInvoiceId()))
                    ).toList();
            commodityMapper.insert(commodityDOList);
            return R.ok();
        } catch (IOException e) {
            throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
        }
    }
}
