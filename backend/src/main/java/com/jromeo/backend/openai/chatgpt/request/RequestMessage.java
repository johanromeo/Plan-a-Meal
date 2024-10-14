package com.jromeo.backend.openai.chatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class is responsible for setting the role and content for OpenAI's chat completion.
 * Is a part of {@link RequestBuilder}.
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

    public enum Role {
        @JsonProperty("system")
        SYSTEM,
        @JsonProperty("user")
        USER
    }
}
