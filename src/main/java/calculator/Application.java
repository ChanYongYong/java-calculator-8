package calculator;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        DelimiterHandler delimiterHandler = new DelimiterHandler();

        // 사용자 입력 받기
        String userInput = inputHandler.getInput();
        // "\n" 이스케이프 처리
        userInput = userInput.replace("\\n", "\n");

        // 구분자를 기준으로 숫자 분리
        String[] numbers = delimiterHandler.splitNumbers(userInput);

        // 분리된 숫자 배열 출력
        System.out.println(Arrays.toString(numbers));
    }
}
