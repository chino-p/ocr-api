package com.trilight.ocr.client;

import com.trilight.ocr.client.erp.ErpClient;
import com.trilight.ocr.client.erp.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootTest
class ErpClientTest {

    @Test
    void testNo() {
        String contractNo = "MP20211109001";
        System.out.println(contractNo.substring(2));
    }

    @Test
    void testRequest() throws Exception {

        StdData<RequestParameter> stdData = new StdData<>();
        ErpRequest<RequestParameter> erpRequest = new ErpRequest<>();
        erpRequest.setStdData(stdData);
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setPageNo(1);
        requestParameter.setPageSize(100000);
        Conditions condition = new Conditions();
        Field field1 = new Field();
        field1.setFieldName("customer_no");
        field1.setValue("0003");
        field1.setOperator("=");
        Field field2 = new Field();
        field2.setFieldName("doc_type_no");
        field2.setValue("2201");
        field2.setOperator("=");
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field1);
        fieldList.add(field2);
        condition.setFields(fieldList);
        condition.setOperator("AND");
        stdData.setParameter(requestParameter);
        requestParameter.setConditions(condition);

        ErpRequest<ResultParameter<SalesOrder>> request = ErpClient.request(erpRequest,
                "yf.oapi.sales.order.data.query.get", "0001", SalesOrder.class);
        System.out.println(request);
    }

    @Test
    void testRequest1() throws Exception {

        StdData<ReadQueryParameter> stdData = new StdData<>();
        ErpRequest<ReadQueryParameter> erpRequest = new ErpRequest<>();
        erpRequest.setStdData(stdData);
        ReadQueryParameter requestParameter = new ReadQueryParameter();
        List<DataKey> dataKeyList = new ArrayList<>();
        DataKey dataKey = new DataKey();
        dataKey.setDocTypeNo("2201");
        dataKey.setDocNo("20241109001");
        dataKeyList.add(dataKey);
        requestParameter.setDataKeyList(dataKeyList);

        stdData.setParameter(requestParameter);

        ErpRequest<ResultParameter<SalesOrder>> request = ErpClient.request(erpRequest,
                "yf.oapi.sales.order.data.read.get", "0001", SalesOrder.class);
        System.out.println(request);
    }
    @Test
    void testRequest2() throws Exception {

        StdData<RequestParameter> stdData = new StdData<>();
        ErpRequest<RequestParameter> erpRequest = new ErpRequest<>();
        erpRequest.setStdData(stdData);
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setPageNo(1);
        requestParameter.setPageSize(100000);
        Conditions condition = new Conditions();
        Field field1 = new Field();
        field1.setFieldName("supplier_order_no");
        field1.setValue("SF3122937341014");
        field1.setOperator("=");
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field1);
        condition.setFields(fieldList);
        condition.setOperator("AND");
        stdData.setParameter(requestParameter);
        requestParameter.setConditions(condition);
        ErpRequest<ResultParameter<GoodsReceipt>> request = ErpClient.request(erpRequest,
                "yf.oapi.purchase.receipt.data.query.get","0001", GoodsReceipt.class);
        List<GoodsReceipt> rows = request.getStdData().getParameter().getQueryResult().getRows();
        System.out.println(rows);
    }

    @Test
    void testTime() {
        System.out.println(TimeZone.getDefault());
    }
}
