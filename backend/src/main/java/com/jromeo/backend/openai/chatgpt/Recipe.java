package com.jromeo.backend.openai.chatgpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
    @JsonProperty("title")
    private String title;
    @JsonProperty("instructions")
    private List<String> instructions;
}
