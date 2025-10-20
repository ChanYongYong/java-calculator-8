package calculator;

import java.util.regex.Pattern;

public class DelimiterHandler {

    private static final String DEFAULT_DELIMITER = ",:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String LINE_SEPARATOR = "\n";

    // 커스텀 구분자 처리를 위해 "//"와 "\n" 제거
    public String extractNumbersPart(String input) {
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int newlineIndex = input.indexOf(LINE_SEPARATOR);
            if (newlineIndex == -1) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
            }
            return input.substring(newlineIndex + 1);
        }
        return input;
    }

    // 입력 문자열에서 구분자(기본 + 커스텀) 추출
    public String getDelimiters(String input) {
        String delimiters = DEFAULT_DELIMITER;
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int newlineIndex = input.indexOf(LINE_SEPARATOR);
            if (newlineIndex == -1) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
            }
            String customDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), newlineIndex);
            delimiters = customDelimiter + delimiters;
        }
        return delimiters;
    }

    // 숫자 부분을 구분자를 기준으로 배열로 분리
    public String[] splitNumbers(String input) {
        String numbersPart = extractNumbersPart(input);
        String delimiters = getDelimiters(input);

        StringBuilder regexBuilder = new StringBuilder();
        for (char ch : delimiters.toCharArray()) {
            regexBuilder.append(Pattern.quote(String.valueOf(ch))).append("|");
        }
        String regex = regexBuilder.substring(0, regexBuilder.length() - 1);

        return numbersPart.split(regex);
    }
}
