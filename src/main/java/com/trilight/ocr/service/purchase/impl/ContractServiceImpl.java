package com.trilight.ocr.service.purchase.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trilight.ocr.mapper.sales.ContractMapper;
import com.trilight.ocr.model.pojo.sales.ContractDO;
import com.trilight.ocr.service.purchase.ContractService;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, ContractDO> implements ContractService {
}
