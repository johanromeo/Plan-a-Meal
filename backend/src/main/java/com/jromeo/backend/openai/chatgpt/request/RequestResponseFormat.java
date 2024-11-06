package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Request response format.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@AllArgsConstructor
public class RequestResponseFormat {

    @JsonProperty("type")
    private final FormatType formatType;

    /**
     * The enum Format type.
     *
     * @author Johan Romeo
     */
    public enum FormatType {
        /**
         * Json object format type.
         *
         * @author Johan Romeo
         */
        @JsonProperty("json_object")
        JSON_OBJECT
    }
}
