package calculator;

public class ResultPrinter {
    private static final String RESULT_FORMAT = "결과 : %d";

    // 계산 결과 출력
    public void printResult(int result) {
        System.out.printf(RESULT_FORMAT + "\n", result);
    }

    // 오류 메시지 출력
    public void printError(String message) {
        System.out.println(message);
    }

}
