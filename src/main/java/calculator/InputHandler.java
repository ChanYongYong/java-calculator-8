package calculator;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {
    // 문자열 입력
    private static final String PROMPT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    // 안내 메시지 출력
    private void printPrompt() {
        System.out.println(PROMPT_MESSAGE);
    }
    public String getInput() {
        printPrompt();
        return Console.readLine();
    }

}
