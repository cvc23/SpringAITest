package com.amdocs.ai.hackathon.hackathon.repository;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PromptRepository {


    @Autowired
    private ChatClient chatClient;

    public List<Generation> callChatGPT(String message, String projectLanguage){
        String userText = """
            Create a document for the given java class...
            """;

        Message userMessage = new UserMessage(userText);

        String systemText = """
            You are software developer that will write documentation for a project in {projectLanguage} .
            """;

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(
                Map.of("projectLanguage", projectLanguage)
        );

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        List<Generation> response = chatClient.call(prompt).getResults();

        return response;
    }

}
