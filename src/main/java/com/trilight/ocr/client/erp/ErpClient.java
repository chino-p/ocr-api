package com.trilight.ocr.client.erp;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilight.ocr.client.erp.model.ErpRequest;
import com.trilight.ocr.client.erp.model.RequestParameter;
import com.trilight.ocr.client.erp.model.ResultParameter;

public class ErpClient {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ErpRequest<ResultParameter> request(ErpRequest<RequestParameter> request, String serviceName) throws
            JsonProcessingException {
        // API URL
        String url = "http://10.0.3.100/YFOAP/openapi.dll/datasnap/rest/TServerMethods1/ATNPost";

        // 构造请求头
        HttpRequest httpRequest = HttpUtil.createPost(url)
                .header("digi-user-token", "68E7C1511D55F57BEC2C0AF86C1085714B7F8AB6560FB177")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("digi-service", String.format("{\"name\":\"%s\"}", serviceName))
                .header("digi-datakey", "{\"CompanyId\":\"0001\"}");

        // 将请求体转换为 JSON 字符串
        String requestBody = objectMapper.writeValueAsString(request);

        HttpResponse response = httpRequest.body(requestBody).execute();

        String responseBody = response.body();
        response.close();

        return objectMapper.readValue(responseBody, objectMapper.getTypeFactory()
                .constructParametricType(ErpRequest.class, ResultParameter.class));
    }
}