package com.quantizer.aiassistant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Finder {
    @Autowired
    private ChatGptService chatGptService;

    public String find(String query, String context) {

        String isrequired=chatGptService.generateResponse(query, context, true);  
        if(isrequired.contains("true")||isrequired.contains("True")||isrequired.contains("TRUE")){
            
            return chatGptService.generateResponse(query, context, false);
        }
        else{
            return chatGptService.generateResponse(query, context, false);
        }
        
}

}
