package calculator;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        // 사용자 입력을 처리하는 객체 생성
        InputHandler inputHandler = new InputHandler();
        // 문자열 계산기를 처리하는 객체 생성
        StringCalculator calculator = new StringCalculator();

        // 사용자 입력 받기
        String userInput = inputHandler.getInput();
        // "\n" 이스케이프 처리
        userInput = userInput.replace("\\n", "\n");

        // 문자열 계산기를 사용하여 합계 계산
        try {
            int result = calculator.calculate(userInput);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
            throw e;
        }
    }
}
