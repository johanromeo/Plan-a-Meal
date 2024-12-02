package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Class for constructing a complete ChatGPT request, specifying which model to use, the request messages
 * aka system prompt and user prompts, and lastly the response format.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@AllArgsConstructor
public class RequestBuilder {

    @JsonProperty("model")
    private final ModelType model;

    @JsonProperty("messages")
    private final List<RequestMessage> message;

    @JsonProperty("response_format")
    private final RequestResponseFormat responseFormat;

    public enum ModelType {

        @JsonProperty("gpt-3.5-turbo")
        GPT_3_5_TURBO
    }
}
