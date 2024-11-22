package com.trilight.ocr.client;

import com.trilight.ocr.client.erp.ErpClient;
import com.trilight.ocr.client.erp.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ErpClientTest {

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

        ErpRequest<ResultParameter> request = ErpClient.request(erpRequest,
                "yf.oapi.sales.order.data.query.get");
        System.out.println(request);
    }
}
