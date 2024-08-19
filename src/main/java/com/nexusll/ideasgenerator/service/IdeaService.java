package com.nexusll.ideasgenerator.service;

import com.nexusll.ideasgenerator.lowService.IOManager;
import com.nexusll.ideasgenerator.lowService.LLMManager;
import com.nexusll.ideasgenerator.model.ApiData;
import com.nexusll.ideasgenerator.model.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IdeaService {
    final int NO_OF_PICKS = 5;

    private final IOManager ioManager;
    private final LLMManager llmManager;

    @Autowired
    public IdeaService(IOManager ioManager, LLMManager llmManager) {
        this.ioManager = ioManager;
        this.llmManager = llmManager;
    }

    public String generateIdea() {
        ArrayList<ApiData> pickedApis = ioManager.pickApis(NO_OF_PICKS);
        Prompt prompt = createPrompt(pickedApis);
        return llmManager.ask(prompt);
    }

    private Prompt createPrompt(ArrayList<ApiData> pickedApis) {
        StringBuilder promptContent = new StringBuilder(Prompt.PROMPT);
        for (ApiData api : pickedApis) {
            promptContent.append(api.toString()).append("\\n");
        }
        return new Prompt(promptContent.toString());
    }

}
