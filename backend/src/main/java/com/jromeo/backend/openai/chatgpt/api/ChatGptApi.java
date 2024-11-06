package com.jromeo.backend.openai.chatgpt.api;

import com.jromeo.backend.openai.chatgpt.request.RequestBuilder;
import com.jromeo.backend.provision.service.ProvisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The type Chat gpt api.
 *
 * @author Johan Romeo
 */
@Service
@Slf4j
public class ChatGptApi {

    @Value("${openai.api-key}")
    private String apiKey;

    private static String CHAT_GPT_URL = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate;
    private final ProvisionService provisionService;

    /**
     * Instantiates a new Chat gpt api.
     *
     * @param restTemplate     the rest template
     * @param provisionService the provision service
     * @author Johan Romeo
     */
    public ChatGptApi(RestTemplate restTemplate, ProvisionService provisionService) {
        this.restTemplate = restTemplate;
        this.provisionService = provisionService;
    }

    /**
     * Call chat gpt api string.
     *
     * @param requestBuilder the request builder
     * @return the string
     * @author Johan Romeo
     */
    public String callChatGptApi(RequestBuilder requestBuilder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        try {
            HttpEntity<RequestBuilder> requestEntity = new HttpEntity<>(requestBuilder, headers);
            ResponseEntity<String> response = restTemplate.exchange(CHAT_GPT_URL, HttpMethod.POST, requestEntity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

        } catch (Exception e) {
            log.warn("Error with chat completion: {}.", e.getMessage());
        }
        //TODO: Don't return null
        return null;
    }
}
