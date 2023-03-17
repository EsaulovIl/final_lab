import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator{
    private String expression;
    private String[] parts_of_expression;
    public Calculator(String expression){
        this.expression = expression;
        this.parts_of_expression = expression.split(" ");
    }
    public boolean check_plus(){
        return this.expression.contains(" + ");
    }
    public boolean check_minus(){
        return this.expression.contains(" - ");
    }
    public boolean check_sign_of_multi(){
        return this.expression.contains(" * ");
    }
    public boolean check_sign_of_div(){
        return this.expression.contains(" : ");
    }
    public boolean check_open_bracket(){
        return this.expression.contains("(");
    }
    public boolean check_close_bracket(){
        return this.expression.contains(")");
    }
    public String multi(){
        int i=0;
        this.parts_of_expression = this.expression.split(" ");
        String[] parts_of_fraction = new String[2];
        Fraction fraction_1, fraction_2;
        while (this.expression.contains(" * ")){
            if (this.parts_of_expression[i].equals("*")){
                parts_of_fraction = this.parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = this.parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                this.expression = this.expression.replace(this.parts_of_expression[i-1] + " " + this.parts_of_expression[i] + " " + this.parts_of_expression[i+1], Fraction.toString(fraction_1.multi(fraction_2)));
                this.parts_of_expression = this.expression.split(" ");
            }
            else{
                i++;
            }
        }
        return this.expression;
    }
    public String div(){
        int i=0;
        this.parts_of_expression = this.expression.split(" ");
        String[] parts_of_fraction = new String[2];
        Fraction fraction_1, fraction_2;
        while (this.expression.contains(" : ")){
            if (this.parts_of_expression[i].equals(":")){
                parts_of_fraction = this.parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = this.parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                this.expression = this.expression.replace(this.parts_of_expression[i-1] + " " + this.parts_of_expression[i] + " " + this.parts_of_expression[i+1], Fraction.toString(fraction_1.div(fraction_2)));
                this.parts_of_expression = this.expression.split(" ");
            }
            else{
                i++;
            }
        }
        return this.expression;
    }
    public String sum(){
        int i=0;
        this.parts_of_expression = this.expression.split(" ");
        String[] parts_of_fraction = new String[2];
        Fraction fraction_1, fraction_2;
        while (this.expression.contains(" + ")){
            if (this.parts_of_expression[i].equals("+")){
                parts_of_fraction = this.parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = this.parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                this.expression = this.expression.replace(this.parts_of_expression[i-1] + " " + this.parts_of_expression[i] + " " + this.parts_of_expression[i+1], Fraction.toString(fraction_1.sum(fraction_2)));
                this.parts_of_expression = this.expression.split(" ");
            }
            else{
                i++;
            }
        }
        return this.expression;
    }
    public String sub(){
        int i=0;
        this.parts_of_expression = this.expression.split(" ");
        String[] parts_of_fraction = new String[2];
        Fraction fraction_1, fraction_2;
        while (this.expression.contains(" - ")){
            if (this.parts_of_expression[i].equals("-")){
                parts_of_fraction = this.parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = this.parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                this.expression = this.expression.replace(this.parts_of_expression[i-1] + " " + this.parts_of_expression[i] + " " + this.parts_of_expression[i+1], Fraction.toString(fraction_1.sub(fraction_2)));
                this.parts_of_expression = this.expression.split(" ");
            }
            else{
                i++;
            }
        }
        return this.expression;
    }
    public String calculate(){
        if (this.check_open_bracket() && this.check_close_bracket()){
            return this.calculateWithBracket();
        }
        else{
            return this.calculateWithoutBracket();
        }
    }
    public String calculateWithBracket(){
        Pattern pattern_of_exp_with_br = Pattern.compile("\\([+\\-/*: 0-9]{1,}\\)");
        while (this.expression.contains("(") && this.expression.contains(")")) {
            Matcher matcher_of_exp_with_br = pattern_of_exp_with_br.matcher(this.expression);
            String exp;
            while (matcher_of_exp_with_br.find()) {
                exp = matcher_of_exp_with_br.group();
                this.expression = (this.expression).replace(exp, Calculator.calculateWithoutBracket(exp.substring(exp.indexOf("(") + 1, exp.indexOf(")"))));
            }
        }
        return this.calculateWithoutBracket();
    }
    public String calculateWithoutBracket(){
        if (this.check_sign_of_multi()){
            this.multi();
        }
        if (this.check_sign_of_div()){
            this.div();
        }
        if (this.check_minus()){
            this.sub();
        }
        if (this.check_plus()){
            this.sum();
        }
        return this.expression;
    }
    public static String calculateWithoutBracket(String expression){
        Fraction fraction_1, fraction_2;
        String[] parts_of_fraction, parts_of_expression;
        parts_of_expression = expression.split(" ");
        int i=0;
        while (expression.contains(" * ")){
            if (parts_of_expression[i].equals("*")){
                parts_of_fraction = parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                expression = expression.replace(parts_of_expression[i-1] + " " + parts_of_expression[i] + " " + parts_of_expression[i+1], Fraction.toString(fraction_1.multi(fraction_2)));
                parts_of_expression = expression.split(" ");
            }
            else{
                i++;
            }
        }
        i = 0;
        while (expression.contains(" : ")){
            if (parts_of_expression[i].equals(":")){
                parts_of_fraction = parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                expression = expression.replace(parts_of_expression[i-1] + " " + parts_of_expression[i] + " " + parts_of_expression[i+1], Fraction.toString(fraction_1.div(fraction_2)));
                parts_of_expression = expression.split(" ");
            }
            else{
                i++;
            }
        }
        i = 0;
        while (expression.contains(" - ")){
            if (parts_of_expression[i].equals("-")){
                parts_of_fraction = parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                expression = expression.replace(parts_of_expression[i-1] + " " + parts_of_expression[i] + " " + parts_of_expression[i+1], Fraction.toString(fraction_1.sub(fraction_2)));
                parts_of_expression = expression.split(" ");
            }
            else{
                i++;
            }
        }
        i = 0;
        while (expression.contains(" + ")){
            if (parts_of_expression[i].equals("+")){
                parts_of_fraction = parts_of_expression[i-1].split("/");
                fraction_1 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                parts_of_fraction = parts_of_expression[i+1].split("/");
                fraction_2 = new Fraction(Integer.parseInt(parts_of_fraction[0]), Integer.parseInt(parts_of_fraction[1]));
                expression = expression.replace(parts_of_expression[i-1] + " " + parts_of_expression[i] + " " + parts_of_expression[i+1], Fraction.toString(fraction_1.sum(fraction_2)));
                parts_of_expression = expression.split(" ");
            }
            else{
                i++;
            }
        }
        return expression;
    }
}