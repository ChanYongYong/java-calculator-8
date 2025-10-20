package calculator;

public class StringCalculator {

    private final DelimiterHandler delimiterHandler;

    //DelimiterHandler 객체 생성(커스텀/기본 구분자 처리를 위해)
    public StringCalculator() {
        this.delimiterHandler = new DelimiterHandler();
    }

    /**
     * 입력 문자열을 받아 숫자를 합산
     * @param input 사용자 입력 문자열
     * @return 숫자 합계
     * @throws IllegalArgumentException 음수나 잘못된 숫자 형식일 경우
     */
    public int calculate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0; // 빈 문자열은 0 반환
        }

        // DelimiterHandler를 사용하여 입력 문자열에서 숫자 부분을 구분자 기준으로 분리
        String[] numbers = delimiterHandler.parseAndSplitInput(input);
        int sum = 0;

        for (String numStr : numbers) {
            if (numStr.isEmpty()) continue;

            int number;
            try {
                number = Integer.parseInt(numStr); // 문자열을 숫자로 변환
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 숫자 형식: " + numStr);
            }

            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }

            sum += number;
        }

        return sum;
    }
}
