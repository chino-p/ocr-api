package com.trilight.ocr.service.purchase.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.trilight.ocr.client.ocr.BaiduOcrClient;
import com.trilight.ocr.common.model.PageQuery;
import com.trilight.ocr.common.model.PageResult;
import com.trilight.ocr.common.model.R;
import com.trilight.ocr.enums.BizCodeEnum;
import com.trilight.ocr.exception.BizException;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public R<Void> uploadInvoiceFiles(MultipartFile[] files) {
        try {
            List<String> base64Contents = FileProcessUtil.processFile(files);

            List<VATInvoiceDTO> vatInvoiceDTOList = BaiduOcrClient.parseVatInvoice(base64Contents);

            List<VATInvoiceDO> vatInvoiceDOList = BeanUtil.copyToList(vatInvoiceDTOList, VATInvoiceDO.class);
            Db.saveBatch(vatInvoiceDOList);
            List<CommodityDO> commodityDOList = vatInvoiceDOList.stream()
                    .flatMap(vatInvoiceDO -> vatInvoiceDO.getCommodityList().stream().peek(commodityDO -> commodityDO.setVatInvoiceId(vatInvoiceDO.getVatInvoiceId()))
                    ).toList();
            Db.saveBatch(commodityDOList);
            return R.ok();
        } catch (IOException e) {
            throw new BizException(BizCodeEnum.FILE_PROCESS_ERROR);
        }
    }

    @Override
    public PageResult<VATInvoiceDTO> pageVATInvoice(PageQuery pageQuery, VATInvoiceDTO vatInvoiceDTO) {
        IPage<VATInvoiceDTO> vatInvoiceDTOIPage = baseMapper.pageVATInvoice(pageQuery.build(), vatInvoiceDTO);
        return PageResult.build(vatInvoiceDTOIPage);
    }

    @Override
    public void updateVATInvoice(Long vatInvoiceId, VATInvoiceDTO vatInvoiceDTO) {
        VATInvoiceDO vatInvoiceDO = BeanUtil.copyProperties(vatInvoiceDTO, VATInvoiceDO.class);
        vatInvoiceDO.setVatInvoiceId(vatInvoiceId);
        updateById(vatInvoiceDO);
    }
}
