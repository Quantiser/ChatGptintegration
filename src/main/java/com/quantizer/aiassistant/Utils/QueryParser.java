package com.quantizer.aiassistant.Utils;

public class QueryParser {

    public static class Result {
        private final boolean requiresInternetData;
        private final String sourceType;
        private final String url;

        public Result(boolean requiresInternetData, String sourceType, String url) {
            this.requiresInternetData = requiresInternetData;
            this.sourceType = sourceType;
            this.url = url;
        }

        public boolean requiresInternetData() {
            return requiresInternetData;
        }

        public String getSourceType() {
            return sourceType;
        }

        public String getUrl() {
            return url;
        }
    }

    public static Result parseQuery(String query) {
        // Implement your query parsing logic here
        // This is a placeholder, replace with your own logic
        boolean requiresInternetData = query.contains("GitHub");
        String sourceType = "github";
        String url = "https://github.com/example/example-repo";

        return new Result(requiresInternetData, sourceType, url);
    }
}
