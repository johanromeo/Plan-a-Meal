package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class RequestBuilderDto {
    @JsonProperty("model")
    private final String model;
    @JsonProperty("messages")
    private final List<RequestMessage> message;
    @JsonProperty("response_format")
    private final RequestResponseFormat responseFormat;

}
