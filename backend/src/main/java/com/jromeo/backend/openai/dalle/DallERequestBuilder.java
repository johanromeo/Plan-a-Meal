package com.jromeo.backend.openai.dalle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
public class DallERequestBuilder {
    private final String model;
    private final String prompt;
    private final Integer n;
    private final String size;
    @JsonProperty("response_format")
    private final String responseFormat;
}
