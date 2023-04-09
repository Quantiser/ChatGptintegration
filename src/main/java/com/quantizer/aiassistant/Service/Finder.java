package com.quantizer.aiassistant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Finder {
    @Autowired
    private ChatGptService chatGptService;

    public String find(String query, String context) {

        String isrequired=chatGptService.generateResponse(query, context, true);  

        if(isrequired.equals("yes")){
            return chatGptService.generateResponse(query, context, false);
        }
        else{
            return "No data found";
        }
}

}
