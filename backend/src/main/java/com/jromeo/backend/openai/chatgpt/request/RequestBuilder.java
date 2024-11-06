package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * The type Request builder.
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

    /**
     * The enum Model type.
     *
     * @author Johan Romeo
     */
    public enum ModelType {
        /**
         * Gpt 3 5 turbo model type.
         *
         * @author Johan Romeo
         */
        @JsonProperty("gpt-3.5-turbo")
        GPT_3_5_TURBO
    }
}
