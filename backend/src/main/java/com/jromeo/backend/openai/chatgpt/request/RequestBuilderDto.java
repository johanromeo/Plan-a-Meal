package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class RequestBuilderDto {
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
