package com.nexusll.ideasgenerator.service;

import com.nexusll.ideasgenerator.lowService.IOManager;
import com.nexusll.ideasgenerator.lowService.LLMManager;
import com.nexusll.ideasgenerator.model.ApiData;
import com.nexusll.ideasgenerator.model.Conversation;
import com.nexusll.ideasgenerator.model.Message;
import com.nexusll.ideasgenerator.model.Prompt;
import com.nexusll.ideasgenerator.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {
    private static final int NO_OF_PICKS = 5;

    private final IOManager ioManager;
    private final LLMManager llmManager;
    private final ConversationRepository conversationRepository;

    @Autowired
    public IdeaService(IOManager ioManager, LLMManager llmManager, ConversationRepository conversationRepository) {
        this.ioManager = ioManager;
        this.llmManager = llmManager;
        this.conversationRepository = conversationRepository;
        ioManager.readApiListFIle();
    }

    public String generateIdea(Prompt prompt, Long conversationId) {
        List<ApiData> pickedApis = ioManager.pickApis(NO_OF_PICKS);
        Prompt promptObject = createPrompt(pickedApis, prompt);
        String jsonString = formatResponse(extractContentFromJson(llmManager.ask(promptObject)));
        saveConversation(conversationId, prompt.getSequence(), jsonString);
        System.out.println(jsonString);
        return jsonString;
    }

    public String chatWithLLM(Prompt prompt, Long conversationId) {
        Conversation conversation = getOrCreateConversation(conversationId);
        Prompt promptObject = createPromptForChat(prompt, conversation);
        String jsonString = formatResponse(extractContentFromJson(llmManager.ask(promptObject)));
        saveConversation(conversation.getId(), prompt.getSequence(), jsonString);
        System.out.println(jsonString);
        return jsonString;
    }

    private Prompt createPrompt(List<ApiData> pickedApis, Prompt prompt) {
        StringBuilder promptContent = new StringBuilder();

        if (prompt != null && prompt.getSequence() != null && !prompt.getSequence().isEmpty()) {
            promptContent.append(prompt.getSequence()).append("\\n");
        } else {
            promptContent.append(Prompt.PROMPT).append("\\n");
        }

        for (ApiData api : pickedApis) {
            promptContent.append(api.toString()).append("\\n");
        }

        return new Prompt(promptContent.toString());
    }

    private Prompt createPromptForChat(Prompt prompt, Conversation conversation) {
        StringBuilder promptContentForChat = new StringBuilder();
        for (Message message : conversation.getMessages()) {
            promptContentForChat.append(message.getRole()).append(": ").append(message.getContent()).append("\n");
        }
        promptContentForChat.append("Human: ").append(prompt.getSequence()).append("\\n");
        promptContentForChat.append("Assistant: ");
        System.out.println(promptContentForChat);
        return new Prompt(promptContentForChat.toString());
    }

    private Conversation getOrCreateConversation(Long conversationId) {
        if (conversationId == null) {
            return new Conversation();
        }
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
    }

    private void saveConversation(Long conversationId, String userMessage, String assistantResponse) {
        Conversation conversation = getOrCreateConversation(conversationId);
        conversation.addMessage(new Message(userMessage, "Human"));
        conversation.addMessage(new Message(assistantResponse, "Assistant"));
        conversationRepository.save(conversation);
    }

    private String extractContentFromJson(String jsonString) {
        int startIndex = jsonString.indexOf("\"content\":\"") + 10;
        int endIndex = jsonString.indexOf("\"}", startIndex);
        return jsonString.substring(startIndex, endIndex);
    }

    private String formatResponse(String response) {
        return response.replace("\\n", "<br>").replace("\\\"", "\"");
    }
}
