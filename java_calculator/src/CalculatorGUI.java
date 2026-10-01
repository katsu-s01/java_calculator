import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {

    private JTextField display;

    // 計算に使用する変数
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public CalculatorGUI() {

        // ウィンドウの設定
        setTitle("電卓");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 全体のレイアウト
        setLayout(new BorderLayout());

        // 表示欄
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        // ボタン配置
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", ".", "+",
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));

            button.addActionListener(e -> buttonPressed(text));

            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // イコールボタン
        JButton equalButton = new JButton("=");
        equalButton.setFont(new Font("Arial", Font.PLAIN, 24));
        equalButton.addActionListener(e -> calculate());

        add(equalButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ボタンが押されたときの処理
    private void buttonPressed(String text) {

        // クリア
        if (text.equals("C")) {
            display.setText("0");
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
            return;
        }

        // 演算子
        if (text.equals("+") || text.equals("-")
                || text.equals("*") || text.equals("/")) {

            firstNumber = Double.parseDouble(display.getText());
            operator = text;
            startNewNumber = true;
            return;
        }

        // 数字・小数点
        if (startNewNumber) {
            display.setText(text.equals(".") ? "0." : text);
            startNewNumber = false;
        } else {
            if (text.equals(".") && display.getText().contains(".")) {
                return;
            }

            display.setText(display.getText() + text);
        }
    }

    // 計算処理
    private void calculate() {

        if (operator.isEmpty()) {
            return;
        }

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":
                if (secondNumber == 0) {
                    display.setText("0で割れません");
                    operator = "";
                    startNewNumber = true;
                    return;
                }
                result = firstNumber / secondNumber;
                break;
        }

        display.setText(String.valueOf(result));

        // 次の計算に備えて初期化
        operator = "";
        startNewNumber = true;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new CalculatorGUI();
        });
    }
}