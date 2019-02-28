package ru.j4jdraft.netw.bot;

import java.util.Map;

public class WiseOracle implements Oracle {
    private static final String DEFAULT_RESPONSE = "I don't know";

    private final Map<String, String> answers;

    public WiseOracle(Map<String, String> answers) {
        this.answers = answers;
    }

    @Override
    public String reply(String request) {
        String[] words = request.split(" ");
        for (String key : words) {
            String answer = answers.get(key);
            if (answer != null) {
                return answer;
            }
        }
        return DEFAULT_RESPONSE;
    }
}
