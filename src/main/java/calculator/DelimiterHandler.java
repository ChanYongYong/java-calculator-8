package calculator;

import java.util.regex.Pattern;

public class DelimiterHandler {

    private static final String DEFAULT_DELIMITER = ",:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String LINE_SEPARATOR = "\n";


    /**
     * 구분자와 숫자 부분을 담는 레코드 클래스
     *
     * @param delimiters  모든 구분자를 포함한 문자열
     * @param numbersPart 실제 계산할 숫자들이 포함된 부분
     */
    private record ParseResult(String delimiters, String numbersPart) {
    }


    /**
     * 입력 문자열을 구분자와 숫자 부분으로 파싱
     *
     * @param input 파싱할 입력 문자열
     * @return 구분자와 숫자 부분이 분리된 객체
     * @throws IllegalArgumentException 구분자 형식이 잘못된 경우
     */
    private ParseResult parseInput(String input) {
        String delimiters = DEFAULT_DELIMITER;
        String numbersPart = input;

        // 사용자 정의 구분자가 있는 경우 처리
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int newlineIndex = input.indexOf(LINE_SEPARATOR);
            if (newlineIndex == -1) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
            }
            // 사용자 정의 구분자 추출
            String customDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), newlineIndex);
            // 사용자 정의 구분자와 기본 구분자 결합
            delimiters = customDelimiter + delimiters;
            // 숫자 부분만 추출
            numbersPart = input.substring(newlineIndex + 1);
        }

        return new ParseResult(delimiters, numbersPart);
    }

    /**
     * 입력 문자열에서 구분자를 파싱하고 숫자 부분을 분리하여 배열로 반환
     * @param input 처리할 입력 문자열
     * @return 구분자로 분리된 숫자 문자열 배열
     */
    public String[] parseAndSplitInput(String input) {

        // 구분자와 숫자 부분 분리
        ParseResult result = parseInput(input);
        // 구분자 문자들을 OR 연산자(|)로 연결된 정규식으로 변환
        StringBuilder regexBuilder = new StringBuilder();
        for (char ch : result.delimiters.toCharArray()) {
            // 특수문자를 정규식에서 사용할 수 있도록 이스케이프 처리
            regexBuilder.append(Pattern.quote(String.valueOf(ch))).append("|");
        }
        // 마지막 | 문자 제거
        String regex = regexBuilder.substring(0, regexBuilder.length() - 1);
        // 정규식 패턴으로 문자열 분할하여 반환
        return result.numbersPart.split(regex);
    }
}
