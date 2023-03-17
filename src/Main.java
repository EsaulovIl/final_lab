import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression;
        while(true) {
            System.out.println("Введите выражение: ");
            expression = input.nextLine().trim();
            if(expression.equals("quit")){
                break;
            }
            else {
                System.out.println("Вывод: ");
                try {
                    String processed_expression = expression.substring(0, 1), sign, previous_sign;
                    int index_of_first_number = Main.firstNumber(expression);
                    for (int index = 1; index < expression.length(); index++) {
                        sign = Character.toString(expression.charAt(index));
                        previous_sign = Character.toString(expression.charAt(index - 1));
                        if ((sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals(":")) && (index_of_first_number < index) && (!previous_sign.equals("(")) && (!previous_sign.equals("/"))) {
                            processed_expression += " " + sign + " ";
                        } else if (sign.equals(" ")) {
                            continue;
                        } else {
                            processed_expression += sign;
                        }
                    }
                    Calculator calculator = new Calculator(processed_expression);
                    System.out.println(calculator.calculate());
                }catch (ArithmeticException x){
                    System.out.println(x.getMessage());
                }catch (Exception y){
                    System.out.println("Ошибка. Некорректное выражение");
                }
            }
        }
    }

    public static int firstNumber(String expression) throws NumberFormatException{
        for (int index = 0; index < expression.length(); index++){
            if(Character.isDigit(expression.charAt(index))){
                return index;
            }
        }
        throw new NumberFormatException("Ошибка. Некорректное выражение");
    }
}