package ru.j4jdraft.netw.bot;

import java.util.Map;

public class WiseOracle implements Oracle {
    public static final String SPLIT_REGEX = "\\s|\\?";

    private final Map<String, String> answers;
    private String defaultResponse = "I don't know";

    public WiseOracle(Map<String, String> answers) {
        this.answers = answers;
        defaultResponse = answers.getOrDefault("default", defaultResponse);
    }

    @Override
    public String reply(String request) {
        String[] words = request.split(SPLIT_REGEX);
        for (String key : words) {
            String answer = answers.get(key);
            if (answer != null) {
                return answer;
            }
        }
        return defaultResponse;
    }
}
