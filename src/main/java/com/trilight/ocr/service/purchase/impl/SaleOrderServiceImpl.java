package com.trilight.ocr.service.purchase.impl;

import com.trilight.ocr.client.erp.model.SalesOrder;
import com.trilight.ocr.service.purchase.SaleOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {
    @Override
    public List<SalesOrder> pageSalesOrder() {

        return List.of();
    }
}
