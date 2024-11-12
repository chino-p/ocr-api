package com.trilight.ocr.client.ocr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilight.ocr.client.ocr.model.CodeResult;
import com.trilight.ocr.client.ocr.model.VATInvoiceData;
import com.trilight.ocr.client.ocr.model.WordsResult;
import com.trilight.ocr.model.dto.purchase.CommodityDTO;
import com.trilight.ocr.model.dto.purchase.VATInvoiceDTO;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaiduOcrClient {

    private BaiduOcrClient () {
    }

    static final RestTemplate restTemplate = new RestTemplate();

    public static final String API_KEY = "GXoa88lZ8k8m9OLcaNNtJkzC";
    public static final String SECRET_KEY = "NTNVJgDPwjFZXe40BMLPKTHrCX702lVe";

    public static List<VATInvoiceDTO> parseVatInvoice(List<String> pdfBase64List) throws IOException {
        String accessToken = getAccessToken();
        List<VATInvoiceDTO> vatInvoiceDTOList = new ArrayList<>();

        for (String pdfBase64 : pdfBase64List) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("pdf_file", pdfBase64);
            body.add("seal_tag", "false");

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

            String url = UriComponentsBuilder
                    .fromHttpUrl("https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice")
                    .queryParam("access_token", accessToken)
                    .toUriString();

            // 发送请求
            ResponseEntity<VATInvoiceData> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                    VATInvoiceData.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
            if (response.getBody() != null) {
                WordsResult wordsResult = response.getBody().getWordsResult();
                VATInvoiceDTO vatInvoiceDTO = new VATInvoiceDTO();
                vatInvoiceDTO.setInvoiceNum(wordsResult.getInvoiceNum());
                vatInvoiceDTO.setInvoiceDate(LocalDate.parse(wordsResult.getInvoiceDate(), formatter));
                vatInvoiceDTO.setSellerName(wordsResult.getSellerName());
                vatInvoiceDTO.setPurchaserName(wordsResult.getPurchaserName());
                vatInvoiceDTO.setTotalAmountIncludeTax(new BigDecimal(wordsResult.getAmountInFiguers()));
                vatInvoiceDTO.setTotalTax(new BigDecimal(wordsResult.getTotalTax()));
                vatInvoiceDTO.setTotalAmount(new BigDecimal(wordsResult.getTotalAmount()));
                int commoditySize = wordsResult.getCommodityName().size();
                List<CommodityDTO> commodityDTOList = new ArrayList<>(commoditySize);
                for (int i = 0; i < commoditySize; i++) {
                    CommodityDTO commodityDTO = buildCommodityDTO(wordsResult, i);
                    commodityDTOList.add(commodityDTO);
                    commodityDTOList.add(commodityDTO);
                }

                vatInvoiceDTO.setCommodityList(commodityDTOList);
                vatInvoiceDTOList.add(vatInvoiceDTO);
            }
        }

        return vatInvoiceDTOList;
    }

    public static String parseCode(String pdfBase64) throws IOException {
        String accessToken = getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("pdf_file", pdfBase64);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://aip.baidubce.com/rest/2.0/ocr/v1/qrcode")
                .queryParam("access_token", accessToken)
                .toUriString();

        ResponseEntity<CodeResult> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                CodeResult.class);
        System.out.println(Objects.requireNonNull(response.getBody()).getCodeResults().get(0).getText().get(0));
        return null;
    }

    private static CommodityDTO buildCommodityDTO(WordsResult wordsResult, int i) {
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityName(wordsResult.getCommodityName().get(i).getWord());
        commodityDTO.setCommodityType(wordsResult.getCommodityType().get(i).getWord());
        commodityDTO.setCommodityUnit(wordsResult.getCommodityUnit().get(i).getWord());
        commodityDTO.setCommodityPrice(new BigDecimal(wordsResult.getCommodityPrice().get(i).getWord()));
        commodityDTO.setCommodityNum(new BigDecimal(wordsResult.getCommodityNum().get(i).getWord()));
        commodityDTO.setCommodityAmount(new BigDecimal(wordsResult.getCommodityAmount().get(i).getWord()));
        commodityDTO.setCommodityTaxRate(
                convertPercentageToDecimal(wordsResult.getCommodityTaxRate().get(i).getWord()));
        commodityDTO.setCommodityTax(new BigDecimal(wordsResult.getCommodityTax().get(i).getWord()));
        return commodityDTO;
    }

    private static BigDecimal convertPercentageToDecimal(String percentage) {
        return new BigDecimal(percentage.replace("%", ""))
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
    }

    private static String getAccessToken() throws IOException {
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
