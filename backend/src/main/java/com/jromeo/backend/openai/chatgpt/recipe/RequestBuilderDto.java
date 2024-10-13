package com.jromeo.backend.openai.chatgpt.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class RequestBuilderDto {
    @JsonProperty("model")
    private final String model;
    @JsonProperty("messages")
    private List<Message> message;
    @JsonProperty("response_format")
    private ResponseFormat responseFormat;

    @Data
    public static class Message {
        @JsonProperty("role")
        private String role;
        @JsonProperty("content")
        private String content;
    }

    @Data
    public static class ResponseFormat {
        @JsonProperty("type")
        private String type;
//        @JsonProperty("json_object")
//        private String jsonObject;
    }
}
