package com.nexusll.ideasgenerator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nexusll.ideasgenerator.model.Prompt;
import com.nexusll.ideasgenerator.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class IdeaRestController {

    private final IdeaService ideaService;

    @Autowired
    public IdeaRestController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping("/generate")
    public String generateIdea(@RequestBody(required = false) Prompt prompt,
                               @RequestParam(required = false) Long conversationId) throws JsonProcessingException {
        return ideaService.generateIdea(prompt, conversationId);
    }

    @PostMapping("/chat")
    public String chatWithLLM(@RequestBody(required = false) Prompt prompt,
                              @RequestParam(required = false) Long conversationId) throws JsonProcessingException {
        return ideaService.chatWithLLM(prompt, conversationId);
    }
}
