package com.fb.xujimanage.util.zhongtaiapi;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;


public class ApiCallHandler {

    private ApiClient apiClient;

    public ApiCallHandler(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String generateAuthorizationHeader(String uri, String methodType, String timestamp) {
        String signatureEncrypt = generateSignature(uri, methodType, timestamp);
        return new StringBuilder("signature credential=")
                .append(apiClient.getAppKeyId()).append(",")
                .append("signature=").append(signatureEncrypt).toString();
    }

    private String generateSignature(String uri, String methodType, String timestamp) {
        String signature = new StringBuilder(apiClient.getAppKeyId())
                .append(uri)
                .append(methodType.toUpperCase())
                .append(timestamp)
                .toString();
        return encryptSignature(signature);
    }


    private String encryptSignature(String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            String encryptKey = apiClient.getAppSecretKey();
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptKey.getBytes(Charset.forName("UTF-8")), mac.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] signatureBytes = mac.doFinal(signature.getBytes(Charset.forName("UTF-8")));
            return DatatypeConverter.printHexBinary(signatureBytes).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static String getAuthorizeHeader(String methodType, String uri, String timestamp, String appKey, String appSecretKey) {
        ApiClient client = new ApiClient(appKey, appSecretKey);
        ApiCallHandler handler = new ApiCallHandler(client);
        return handler.generateAuthorizationHeader(uri, methodType, timestamp);
    }
}
