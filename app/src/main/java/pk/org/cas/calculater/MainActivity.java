package pk.org.cas.calculater;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


    TextView ed_screen;
    MaterialButton mb_clear, mb_del, mb_modules, mb_divide, mb_multiply, mb_subtract, mb_add, mb_equal, mb_one, mb_two, mb_three, mb_four, mb_five, mb_six, mb_seven, mb_eight, mb_nine, mb_zero, mb_dot, unit_measure;

    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        ed_screen = findViewById(R.id.ed_screen);

        mb_add = findViewById(R.id.mb_add);
        mb_divide = findViewById(R.id.mb_divide);
        mb_multiply = findViewById(R.id.mb_multiply);
        mb_subtract = findViewById(R.id.mb_subtract);
        mb_modules = findViewById(R.id.mb_modules);
        mb_clear = findViewById(R.id.mb_clear);
        mb_del = findViewById(R.id.mb_del);
        mb_equal = findViewById(R.id.mb_equal);
        mb_dot = findViewById(R.id.mb_dot);
        mb_one = findViewById(R.id.mb_one);
        mb_two = findViewById(R.id.mb_two);
        mb_three = findViewById(R.id.mb_three);
        mb_four = findViewById(R.id.mb_four);
        mb_five = findViewById(R.id.mb_five);
        mb_six = findViewById(R.id.mb_six);
        mb_seven = findViewById(R.id.mb_seven);
        mb_eight = findViewById(R.id.mb_eight);
        mb_nine = findViewById(R.id.mb_nine);
        mb_zero = findViewById(R.id.mb_zero);
        unit_measure = findViewById(R.id.unit_measure);

        // unit measure button click listener
        unit_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, unitCalculate.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Unit Measure", Toast.LENGTH_SHORT).show();
            }
        });


        ed_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_screen.getText().length() < 15) {
                    Toast.makeText(MainActivity.this, "Character count is less than 15", Toast.LENGTH_SHORT).show();
                }
            }
        });

        View.OnClickListener appendListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialButton button = (MaterialButton) v;
                String buttonText = button.getText().toString();
                String currentText = ed_screen.getText().toString();


                if (currentText.length() > 0) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    if (isOperator(lastChar) && isOperator(buttonText.charAt(0))) {
                        Toast.makeText(MainActivity.this, "Consecutive operators not allowed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (isOperator(buttonText.charAt(0))) {
                    for (int i = 0; i < currentText.length(); i++) {
                        if (isOperator(currentText.charAt(i))) {
                            Toast.makeText(MainActivity.this, "Only two operands allowed at a time", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }


                if (currentText.length() == 0) {
                    if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") ||
                            buttonText.equals("/") || buttonText.equals("%") || buttonText.equals(".")) {
                        Toast.makeText(MainActivity.this, "Cannot start with an operator", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (ed_screen.getText().length() < 15) {
                    ed_screen.append(buttonText);
                } else {
                    Toast.makeText(MainActivity.this, "Character limit reached", Toast.LENGTH_SHORT).show();
                }
            }
        };


        mb_one.setOnClickListener(appendListener);
        mb_two.setOnClickListener(appendListener);
        mb_three.setOnClickListener(appendListener);
        mb_four.setOnClickListener(appendListener);
        mb_five.setOnClickListener(appendListener);
        mb_six.setOnClickListener(appendListener);
        mb_seven.setOnClickListener(appendListener);
        mb_eight.setOnClickListener(appendListener);
        mb_nine.setOnClickListener(appendListener);
        mb_zero.setOnClickListener(appendListener);
        mb_dot.setOnClickListener(appendListener);
        mb_add.setOnClickListener(appendListener);
        mb_subtract.setOnClickListener(appendListener);
        mb_multiply.setOnClickListener(appendListener);
        mb_divide.setOnClickListener(appendListener);
        mb_modules.setOnClickListener(appendListener);


        mb_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = ed_screen.getText().toString();
                if (!expression.isEmpty()) {
                    calculateResult(expression);
                    Toast.makeText(MainActivity.this, "Your Result is Here", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mb_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_screen.setText("");
                Toast.makeText(MainActivity.this, "clear all the content", Toast.LENGTH_SHORT).show();
            }
        });


        mb_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ed_screen.getText().toString();
                if (text.length() > 0) {
                    ed_screen.setText(text.substring(0, text.length() - 1));
                }
            }
        });
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private void calculateResult(String expression) {
        char op = ' ';
        int opIndex = -1;

        // Find the operator
        if (expression.contains("+")) {
            op = '+';
            opIndex = expression.indexOf('+');
        } else if (expression.contains("-")) {
            op = '-';
            opIndex = expression.indexOf('-');
        } else if (expression.contains("*")) {
            op = '*';
            opIndex = expression.indexOf('*');
        } else if (expression.contains("/")) {
            op = '/';
            opIndex = expression.indexOf('/');
        } else if (expression.contains("%")) {
            op = '%';
            opIndex = expression.indexOf('%');
        }

        if (opIndex != -1 && opIndex < expression.length() - 1) {
            try {
                double no1 = Double.parseDouble(expression.substring(0, opIndex));
                double no2 = Double.parseDouble(expression.substring(opIndex + 1));

                calculator.setNo1(no1);
                calculator.setNo2(no2);
                calculator.setOperator(op);

                double result = calculator.calculate();

                if (result == (long) result) {
                    ed_screen.setText(String.valueOf((long) result));
                } else {
                    ed_screen.setText(String.valueOf(result));
                }
            } catch (Exception e) {
                ed_screen.setText("Error");
            }
        }
    }
}
