package com.trilight.ocr.client.erp;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilight.ocr.client.erp.model.ErpRequest;
import com.trilight.ocr.client.erp.model.ResultParameter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErpClient {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T, R> ErpRequest<ResultParameter<T>> request(ErpRequest<R> request, String serviceName,
                                                                String companyNo, Class<T> resultClass) throws
            JsonProcessingException {
        String url = "http://10.0.3.100/YFOAP/openapi.dll/datasnap/rest/TServerMethods1/ATNPost";

        HttpRequest httpRequest = HttpUtil.createPost(url)
                .header("digi-user-token", "68E7C1511D55F57BEC2C0AF86C1085714B7F8AB6560FB177")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("digi-service", String.format("{\"name\":\"%s\"}", serviceName))
                .header("digi-datakey", String.format("{\"CompanyId\":\"%s\"}", companyNo));

        String requestBody = objectMapper.writeValueAsString(request);

        HttpResponse response = httpRequest.body(requestBody).execute();

        String responseBody = response.body();
        response.close();

        return objectMapper.readValue(responseBody, objectMapper.getTypeFactory()
                .constructParametricType(ErpRequest.class, objectMapper.getTypeFactory()
                        .constructParametricType(ResultParameter.class, resultClass)));
    }
}