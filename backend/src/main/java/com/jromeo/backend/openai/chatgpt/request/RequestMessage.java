package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Request message.
 *
 * @author Johan Romeo
 */
@Getter
@Setter
@AllArgsConstructor
public class RequestMessage {
    @JsonProperty("role")
    private final Role role;
    @JsonProperty("content")
    private final String content;

    /**
     * The enum Role.
     *
     * @author Johan Romeo
     */
    public enum Role {
        /**
         * System role.
         *
         * @author Johan Romeo
         */
        @JsonProperty("system")
        SYSTEM,
        /**
         * User role.
         *
         * @author Johan Romeo
         */
        @JsonProperty("user")
        USER
    }
}
