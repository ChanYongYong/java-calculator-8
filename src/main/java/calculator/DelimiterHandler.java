package calculator;

import java.util.regex.Pattern;

public class DelimiterHandler {

    private static final String DEFAULT_DELIMITER = ",:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String LINE_SEPARATOR = "\n";


    // 구분자와 숫자부분을 담는 레코드
    private record ParseResult(String delimiters, String numbersPart) {}

    // 입력 문자열 파싱 (내부 메서드)
    private ParseResult parseInput(String input) {
        String delimiters = DEFAULT_DELIMITER;
        String numbersPart = input;

        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int newlineIndex = input.indexOf(LINE_SEPARATOR);
            if (newlineIndex == -1) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
            }
            String customDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), newlineIndex);
            delimiters = customDelimiter + delimiters;
            numbersPart = input.substring(newlineIndex + 1);
        }

        return new ParseResult(delimiters, numbersPart);
    }


    // 숫자 부분을 구분자를 기준으로 배열로 분리
    public String[] parseAndSplitInput(String input) {
        ParseResult result = parseInput(input);

        StringBuilder regexBuilder = new StringBuilder();
        for (char ch : result.delimiters.toCharArray()) {
            regexBuilder.append(Pattern.quote(String.valueOf(ch))).append("|");
        }
        String regex = regexBuilder.substring(0, regexBuilder.length() - 1);

        return result.numbersPart.split(regex);
    }
}
