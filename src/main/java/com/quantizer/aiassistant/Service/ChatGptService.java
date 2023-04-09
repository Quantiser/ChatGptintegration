package com.quantizer.aiassistant.Service;

import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatGptService {

    private static final String GPT_API_BASE_URL = "https://api.openai.com/v1/engines/text-davinci-002/completions";
    private static final String API_KEY = "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";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public String generateResponse(String query, String context, boolean isInitial) {
        try {
            OkHttpClient client = createHttpClient();
            Request request = buildGptRequest(query, context);
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.out.println("API Error: " + response.body().string());
                    throw new IOException("Unexpected code " + response);
                }
    
                // Store the response body in a variable
                String responseBody = response.body().string();
                if (isInitial && responseBody.contains("true") ){
                    return "True";
                }
                // Print the response body to the console
                System.out.println("Response body: " + responseBody);
    
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray choices = jsonResponse.getJSONArray("choices");
                JSONObject firstChoice = choices.getJSONObject(0);
                return firstChoice.getString("text");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to generate response.";
        }
    }
    
    
    

    private OkHttpClient createHttpClient() {
        return new OkHttpClient();
    }

    private Request buildGptRequest(String query, String context) {
        String prompt = "User: " + query + "\nAssistant: ";
        
        if (context != null && !context.isEmpty()) {
            prompt = "Context: " + context + "\n" + prompt;
        }
    
        JSONObject requestBody = new JSONObject();
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 1500);
        requestBody.put("n", 1);
        requestBody.put("temperature", 0.5); 
    
        RequestBody body = RequestBody.create(JSON, requestBody.toString());
        System.out.println("Request body: " + requestBody.toString());
        return new Request.Builder()
                .url(GPT_API_BASE_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();
    }
    
    
    
}
