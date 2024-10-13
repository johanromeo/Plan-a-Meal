package com.jromeo.backend.openai.chatgpt.api;

import com.jromeo.backend.openai.chatgpt.request.RequestBuilder;
import com.jromeo.backend.provision.service.ProvisionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Class responsible for calling OpenAI's Chat completions API.
 *
 * @author Johan Romeo
 */
@Service
public class ChatGptApi {

    @Value("${openai.api-key}")
    private String apiKey;
    @Value("${openai.chatgpt-url}")
    private String chatgptUrl;

    private final RestTemplate restTemplate;
    private final ProvisionService provisionService;

    public ChatGptApi(RestTemplate restTemplate, ProvisionService provisionService) {
        this.restTemplate = restTemplate;
        this.provisionService = provisionService;
    }

    /**
     * Call OpenAI's chat completions URL with provided api key and a customizable request.
     * @param requestBuilder {@link RequestBuilder} - Blueprint for building the API request body.
     * @return
     */
    public String callChatGptApi(RequestBuilder requestBuilder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<RequestBuilder> requestEntity = new HttpEntity<>(requestBuilder, headers);
        ResponseEntity<String> response = restTemplate.exchange(chatgptUrl, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
