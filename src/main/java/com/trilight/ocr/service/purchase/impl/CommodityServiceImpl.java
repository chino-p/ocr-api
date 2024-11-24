package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.purchase.CommodityMapper;
import com.trilight.ocr.model.pojo.purchase.CommodityDO;
import com.trilight.ocr.service.purchase.CommodityService;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, CommodityDO> implements CommodityService {

}
