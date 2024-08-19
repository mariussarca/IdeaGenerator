package com.nexusll.ideasgenerator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nexusll.ideasgenerator.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/generate_ideas")
public class IdeaRestController {

    private final IdeaService ideaService;

    @Autowired
    public IdeaRestController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

//    @CrossOrigin(origins = "file:///C:/Users/Marius/Desktop/Nexus/Programe/IdeasGenerator/src/main/java/com/nexusll/ideasgenerator/model/ideaGenerates.html")
    @GetMapping ("/llm/getIdeas")
    public String generateIdea() throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode obj = mapper.readTree(ideaService.generateIdea());
//        return obj;
        // Example JSON string
        String jsonString = ideaService.generateIdea();
        // Extract the content part from the JSON
        String content = jsonString.substring(
                jsonString.indexOf("\"content\":\"") + 10,
                jsonString.indexOf("\"}", jsonString.indexOf("\"content\":\"") + 10)
        );
        // Return the extracted content
        return content.replace("\\n", "<br>").replace("\\\"", "\"");
    }
}
