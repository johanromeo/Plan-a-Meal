package com.jromeo.backend.openai.chatgpt.api;

import com.jromeo.backend.openai.chatgpt.request.RequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Class for sending requests to ChatGPT's /chat/completions endpoint.
 *
 * @author Johan Romeo
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ChatGptApi {

    @Value("${openai.api-key}")
    private String apiKey;

    private static final String CHAT_GPT_URL = "https://api.openai.com/v1/chat/completions";

    private final RestTemplate restTemplate;

    /**
     * Makes a POST request to ChatGPT API with a {@link RequestBuilder} object consisting of a predefined
     * system prompt and custom user prompts.
     *
     * @param requestBuilder the {@link RequestBuilder} object.
     * @return the response from ChatGPT.
     */
    public String callChatGptApi(RequestBuilder requestBuilder) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        try {
            HttpEntity<RequestBuilder> requestEntity = new HttpEntity<>(requestBuilder, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    CHAT_GPT_URL,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("ChatGPT called: Awaiting response. . .");

                return response.getBody();
            }
        } catch (Exception e) {
            log.warn("Error with chat completion: {}.", e.getMessage());
        }

        return null;
    }
}
