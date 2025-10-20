package calculator;

public class StringCalculator {

    private final DelimiterHandler delimiterHandler;

    //DelimiterHandler 객체 생성(커스텀/기본 구분자 처리를 위해)
    public StringCalculator() {
        this.delimiterHandler = new DelimiterHandler();
    }

    //입력 문자열을 받아 숫자를 합산
    public int calculate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty string cannot be calculated.");
        }

        String[] numbers = delimiterHandler.parseAndSplitInput(input);
        return sumNumbers(numbers);
    }

    // 문자열 배열을 숫자로 변환하고 합산
    private int sumNumbers(String[] numbers) {
        int sum = 0;

        for (String numStr : numbers) {
            if (numStr.isEmpty()) continue;

            int number = parseToPositiveInteger(numStr);
            sum += number;
        }

        return sum;
    }

    // 문자열을 양수 정수로 변환
    private int parseToPositiveInteger(String numStr) {
        int number;
        try {
            number = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자 형식: " + numStr);
        }

        if (number < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
        }

        return number;
    }

}
