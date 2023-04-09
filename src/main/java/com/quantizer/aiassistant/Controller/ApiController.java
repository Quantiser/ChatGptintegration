package com.quantizer.aiassistant.Controller;

import com.quantizer.aiassistant.Service.ChatGptService;
import com.quantizer.aiassistant.Service.DataRetrievalService;
import com.quantizer.aiassistant.Utils.QueryParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

    private final ChatGptService chatGptService;
    private final DataRetrievalService dataRetrievalService;

     public ApiController(ChatGptService chatGptService, DataRetrievalService dataRetrievalService) {
        this.chatGptService = chatGptService;
        this.dataRetrievalService = dataRetrievalService;
    }

    @PostMapping("/processRequest")
    public String processRequest(@RequestBody String query) {
    
        QueryParser.Result parseResult = QueryParser.parseQuery(query);

        String context = parseResult.requiresInternetData() ? dataRetrievalService.fetchData(parseResult) : "";
        return chatGptService.generateResponse(query, context, false);
    }
}
