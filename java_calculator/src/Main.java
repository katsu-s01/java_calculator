import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.Calculator();
    }

    public void Calculator(){
        /*初期値を入力させる*/
        double tmp = 0;
        double result = 0;
        System.out.println("二つ値を改行して入力してください");
        Scanner scanner = new Scanner(System.in);
        double code1 = Double.parseDouble(scanner.nextLine());
        double code2 = Double.parseDouble(scanner.nextLine());

        /*計算する演算子の選択*/
        System.out.println("演算子を選んでください。");
        System.out.println("+, -, *, /");
        Scanner scanner2 = new Scanner(System.in);
        var operator = scanner2.nextLine();

        switch (operator) {
            case "+":
                System.out.println("計算結果:");
                result = code1 + code2;
                System.out.println(result);
                break;
            case "-":
                System.out.println("計算結果:");
                result = code1 - code2;
                System.out.println(result);
                break;
            case "*" :
                System.out.println("計算結果:");
                result = code1 * code2;
                System.out.println(result);
                break;
            case "/":
                if(code2 == 0){
                    System.out.println("0で割ることはできません。");
                }else{
                    System.out.print("計算結果:");
                    result = code1 / code2;
                    System.out.println(result);
                }
                break;
            default:
                System.out.println("選択した演算子は存在しません。");
                break;
        }
    }
}