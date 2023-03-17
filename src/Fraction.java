public class Fraction{

    private int numerator;
    private int denominator;

    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }

    public Fraction(int numerator, int denominator) throws ArithmeticException{
        if (denominator == 0){
            throw new ArithmeticException("Ошибка. Деление на 0.");
        }
        else if (numerator <0 && denominator <0) {
            this.numerator = Math.abs(numerator);
            this.denominator = Math.abs(denominator);
        }
        else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
    public Fraction(){
        numerator = 1;
        denominator = 1;
    }
    public static String toString(Fraction fraction){
        return (fraction.numerator + "/" + (fraction.denominator));
    }
    //Умножение
    public static Fraction multi(Fraction fraction1, Fraction fraction2){
        Fraction res = new Fraction(fraction1.numerator*fraction2.numerator, fraction1.denominator*fraction2.denominator);
        int nod_number = nod(res.numerator, res.denominator);
        res.numerator /= nod_number;
        res.denominator /= nod_number;
        return res;
    }
    public Fraction multi(Fraction fraction){
        Fraction res = new Fraction(fraction.numerator * this.numerator, fraction.denominator * this.denominator);
        int nod_number = nod(res.numerator, res.denominator);
        res.numerator /= nod_number;
        res.denominator /= nod_number;
        return res;
    }
    //Деление
    public static Fraction div(Fraction fraction1, Fraction fraction2) throws ArithmeticException{
        if (fraction2.denominator == 0){
            throw new ArithmeticException("Ошибка. Деление на 0.");
        }
        else {
            Fraction res = new Fraction(fraction1.numerator * fraction2.denominator, fraction1.denominator * fraction2.numerator);
            int nod_number = nod(res.numerator, res.denominator);
            res.numerator /= nod_number;
            res.denominator /= nod_number;
            return res;
        }
    }
    public Fraction div(Fraction fraction) throws ArithmeticException{
        if (fraction.denominator == 0){
            throw new ArithmeticException("Ошибка. Деление на 0.");
        }
        else {
            Fraction res = new Fraction(this.numerator * fraction.denominator, fraction.numerator * this.denominator);
            int nod_number = nod(res.numerator, res.denominator);
            res.numerator /= nod_number;
            res.denominator /= nod_number;
            return res;
        }
    }
    //Сложение
    public static Fraction sum(Fraction fraction1, Fraction fraction2){
        int nok_number = nok(fraction1.denominator, fraction2.denominator);
        Fraction res = new Fraction((fraction1.numerator * (nok_number /fraction1.denominator)) + (fraction2.numerator * (nok_number /fraction2.denominator)), nok_number);
        int nod_number = nod(res.numerator, res.denominator);
        res.numerator /= nod_number;
        res.denominator /= nod_number;
        return res;
    }
    public Fraction sum(Fraction fraction){
        int nok_number = nok(this.denominator, fraction.denominator);
        Fraction res = new Fraction((this.numerator * (nok_number / this.denominator))+(fraction.numerator * (nok_number / fraction.denominator)), nok_number);
        int nod_number = nod(res.numerator, res.denominator);
        res.numerator /= nod_number;
        res.denominator /= nod_number;
        return res;
    }
    //Вычитание
    public static Fraction sub(Fraction fraction1, Fraction fraction2){
        int nok_number = nok(fraction1.denominator, fraction2.denominator);
        Fraction res = new Fraction((fraction1.numerator * (nok_number / fraction1.denominator))-(fraction2.numerator * (nok_number / fraction2.denominator)),nok_number);
        int nod_number = nod(Math.abs(res.numerator), res.denominator);
        res.numerator /= nod_number;
        res.denominator /= nod_number;
        return res;
    }
    public Fraction sub(Fraction fraction){
        int nok_number = nok(this.denominator, fraction.denominator);
        Fraction res = new Fraction((this.numerator * (nok_number / this.denominator))-(fraction.numerator * (nok_number / fraction.denominator)), nok_number);
        int nod_number = nod(Math.abs(res.numerator), res.denominator);
        res.numerator /= nod_number;
        res.denominator /= nod_number;
        return res;
    }
    private static int nod(int number1, int number2){
        number1 = Math.abs(number1);
        number2 = Math.abs(number2);
        if (number1 != 0 && number2 != 0){
            while (number1 != number2) {
                if (number1 > number2) {
                    number1 -= number2;
                } else {
                    number2 -= number1;
                }
            }
            return number1;
        }
        else{
            return 1;
        }
    }
    private static int nok(int number1, int number2){
        int nok_number = number1 * number2 / nod(number1, number2);
        return nok_number;
    }
}