package com.quantizer.aiassistant.Service;

import com.quantizer.aiassistant.Utils.QueryParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataRetrievalService {

    public String fetchData(QueryParser.Result parseResult) {
        if (parseResult.requiresInternetData()) {
            return parseResult.getSourceType().equals("github") ? accessGitHubApi(parseResult) : scrapeWebsite(parseResult);
        }
        return "";
    }

    public String scrapeWebsite(QueryParser.Result parseResult) {
        try {
            Document doc = Jsoup.connect(parseResult.getUrl()).get();
            // Process the HTML document and extract relevant data
            return "Extracted data from the website";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to fetch data from the website.";
        }
    }

    // ...

    public String accessGitHubApi(QueryParser.Result parseResult) {
        // Implement GitHub API access using OkHttpClient or another library.
        // Refer to GitHub REST API documentation for details: https://docs.github.com/en/rest/
        return "Fetched data from GitHub API";
    }
}
