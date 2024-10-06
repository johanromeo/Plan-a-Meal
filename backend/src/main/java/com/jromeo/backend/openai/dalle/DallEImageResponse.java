package com.jromeo.backend.openai.dalle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DallEImageResponse {

    private List<ImageData> data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageData {

        @JsonProperty("b64_json")
        private String b64json;
    }
}
