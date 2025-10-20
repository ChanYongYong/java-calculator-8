package calculator;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        // 사용자 입력을 처리하는 객체 생성
        InputHandler inputHandler = new InputHandler();
        // 문자열 계산기를 처리하는 객체 생성
        StringCalculator calculator = new StringCalculator();
        // 메시지 출력을 처리하는 객체 생성
        ResultPrinter printer = new ResultPrinter();

        // 문자열 계산기를 사용하여 합계 계산
        try {
            // 사용자 입력 받기 Console.readLine() 사용
            String userInput = inputHandler.getInput();
            // "\n" 이스케이프 처리
            userInput = userInput.replace("\\n", "\n");

            //덧셈 계산
            int result = calculator.calculate(userInput);

            // 결과 출력
            printer.printResult(result);
        } catch (IllegalArgumentException e) {
            // 오류 재던짐으로 인해 종료
            printer.printError(e.getMessage());
            throw e;
        }
    }
}
