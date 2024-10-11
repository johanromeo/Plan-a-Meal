package com.jromeo.backend.openai.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class Cot {

    private final ChatGptService chatGptService;

    public Cot(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping
    public ResponseEntity<Recipe> getRecipe() throws JsonProcessingException {
        return new ResponseEntity<>(chatGptService.makeRequest(), HttpStatus.OK);
    }
}
