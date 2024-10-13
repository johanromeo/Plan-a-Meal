package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestResponseFormat {
    @JsonProperty("type")
    private final FormatType formatType;

    public enum FormatType {
        @JsonProperty("json_object")
        JSON_OBJECT
    }
}
