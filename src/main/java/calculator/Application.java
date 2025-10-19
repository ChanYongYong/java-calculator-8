package calculator;

public class Application {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String userInput = inputHandler.getInput();
        System.out.println(userInput);
    }
}
