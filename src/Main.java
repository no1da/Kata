import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws HetTakoyOperaciiException {
        Scanner console = new Scanner(System.in);
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.");
        System.out.println("Используйте целые арабские или римские цифры от 1 до 10. Ведите выражение и нажмите Enter:");
        String userInput = console.nextLine();
        System.out.println(calc(userInput));
    }
    public static String calc(String vvod) throws HetTakoyOperaciiException {
        /*String s = vvod;*/
        String[] s1 = vvod.split(" ");
        proverka(s1);
        String variable1 = s1[0];
        String variable2 = s1[2];
        String operation = s1[1];
        String[] rome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        int a = matchingRomanNumerals(rome, variable1);
        int b = matchingRomanNumerals(rome, variable2);

        String r = null;
        if ((a == 2 && b == 2) || (a != b)) {
            r = null;

            try {
                r = Integer.toString(calculate(variable1, variable2, operation));

            } catch (NumberFormatException e) {
                System.out.println("throws Exception // Используются одновременно разные системы исчисления. Переменные отличные от арабских и " +
                        "римских относятся к иным системам. Используйте целые числа.");
                System.exit(1);
            } catch (HetTakoyOperaciiException e) {
                System.out.println("throws Exception // Строка не является математической операцией.");
                System.exit(1);
            }

        }
        if (a == 1 && b == 1) {
            int res = calculate(replacingArabicNumerals(rome, variable1), replacingArabicNumerals(rome, variable2), operation);
            try {
                r = rome[res - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("throws Exception // Результатом работы калькулятора с римскими числами могут быть " +
                        "только положительные числа, ноль отсутствует в римской системе исчисления.");
                System.exit(1);
            }

        }
        return r;

    }


    static int matchingRomanNumerals(String[] a, String target) {
        int c = 0;
        for (String i : a) {
            if (Objects.equals(target, i)) {
                c = 1;
                break;
            } else {
                c = 2;
            }
        }
        return c;
    }

    static String replacingArabicNumerals(String[] a, String target) {
        int c = 0;
        String s = "Здесь должна быть арабская цифра";
        for (String i : a) {
            c++;
            if (Objects.equals(target, i)) {
                s = String.valueOf(c);
                break;
            }
        }
        return s;
    }

    static int calculate(String variable1, String variable2, String operation) throws HetTakoyOperaciiException {
        int variable1Int = Integer.parseInt(variable1);
        int variable2Int = Integer.parseInt(variable2);
        int res = 999;
        if (operation.equals("+")) {
            res = variable1Int + variable2Int;
        }
        if (operation.equals("-")) {
            res = variable1Int - variable2Int;
        }
        if (operation.equals("*")) {
            res = variable1Int * variable2Int;
        }
        if (operation.equals("/")) {
            res = variable1Int / variable2Int;
        }
        if (res == 999) {
            throw new HetTakoyOperaciiException();
        }
        if ((variable1Int > 10 || variable1Int < 1) || (variable2Int > 10 || variable2Int < 1)) {
            try {
                throw new Bolshe10Exception();
            } catch (Bolshe10Exception e) {
                System.out.println("throws Exception // Введены числа больше 10 либо меньше 1.");
            }
            System.exit(1);
        }
        return res;
    }

    static void proverka(String[] s1) {
        if (s1.length < 3) {
            try {
                throw new HetTakoyOperaciiException();
            } catch (HetTakoyOperaciiException e) {
                System.out.println("throws Exception // Строка не является математической операцией.");
            }
            System.exit(1);
        }
    }
}