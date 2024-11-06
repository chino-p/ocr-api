package com.trilight.orc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;

class Sample {

    static final RestTemplate restTemplate = new RestTemplate();

    public static final String API_KEY = "GXoa88lZ8k8m9OLcaNNtJkzC";
    public static final String SECRET_KEY = "NTNVJgDPwjFZXe40BMLPKTHrCX702lVe";

    public static void main(String[] args) throws IOException {
        String pdfBase64 = getResourceFileContentAsBase64("test.pdf", true);

        // 生成 access token
        String accessToken = getAccessToken();

        // 构建请求体
        String requestBody = "pdf_file=" + pdfBase64 + "&seal_tag=false";

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(MediaType.parseMediaTypes("application/json"));

        // 生成请求
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 构建 URL
        String url = UriComponentsBuilder
                .fromHttpUrl("https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice")
                .queryParam("access_token", accessToken)
                .toUriString();

        // 发送请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

    static String getResourceFileContentAsBase64(String resourcePath, boolean urlEncode) throws IOException {
        // 使用 ClassLoader 获取文件输入流
        try (InputStream inputStream = Sample.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + resourcePath);
            }

            byte[] fileBytes = inputStream.readAllBytes();
            return encodeToBase64(fileBytes, urlEncode);
        }
    }

    // 将文件内容转换为 Base64
    static String encodeToBase64(byte[] fileBytes, boolean urlEncode) {
        String base64 = Base64.getEncoder().encodeToString(fileBytes);
        if (urlEncode) {
            base64 = URLEncoder.encode(base64, java.nio.charset.StandardCharsets.UTF_8);
        }
        return base64;
    }

    static String getAccessToken() throws IOException {
        // 构建请求体
        String requestBody = "grant_type=client_credentials&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建请求实体
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 发送 POST 请求
        String url = "https://aip.baidubce.com/oauth/2.0/token";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 解析响应
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response.getBody(), new TypeReference<>() {
        });
        return (String) map.get("access_token");
    }
}
