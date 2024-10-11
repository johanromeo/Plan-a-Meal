//package com.jromeo.backend.openai.dalle;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Base64;
//import java.util.UUID;
//
//@Service
//@Slf4j
//public class DallEClient {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DallEClient.class);
//
//    private final RestTemplate restTemplate;
//
//    @Value("${openai.api-key}")
//    private String openaiApiKey;
//
//    public DallEClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    //TODO: Method should be in a separate class?
//    //TODO: Fix better impl
//    public void generateAndSaveImage(String prompt) {
//        ResponseEntity<DallEImageResponse> response = callDallEApi(prompt);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            DallEImageResponse dalleImage = response.getBody();
//            String base64Image = dalleImage.getData().get(0).getB64json();
//            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//            saveImage(imageBytes);
//            LOGGER.info("Image saved");
//        } else {
//            LOGGER.info("Error: {}", response.getStatusCode());
//        }
//
//    }
//
//    private ResponseEntity<DallEImageResponse> callDallEApi(String prompt) {
//        final String url = "https://api.openai.com/v1/images/generations";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(openaiApiKey);
//
////        HashMap<String, String> requestBody = new HashMap<>();
////        requestBody.put("prompt", "a baby with blue eyes and light brown hair");
////        requestBody.put("size", "256x256");
////        requestBody.put("response_format", "b64_json");
//        DallERequestBuilder requestBody = DallERequestBuilder
//                .builder()
//                .model("dall-e-2")
//                .prompt(prompt)
//                .n(1)
//                .size("256x256")
//                .responseFormat("b64_json")
//                .build();
//
//        HttpEntity<DallERequestBuilder> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, DallEImageResponse.class);
//    }
//
//    //TODO: Method should be in a separate class?
//    //TODO: Fix better impl
//    private void saveImage(byte[] imageBytes) {
//        String rootDir = "./img";
//        String filePath = rootDir + File.separator + UUID.randomUUID() + ".png";
//
//        try (FileOutputStream fos = new FileOutputStream(filePath)) {
//            fos.write(imageBytes);
//            fos.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
