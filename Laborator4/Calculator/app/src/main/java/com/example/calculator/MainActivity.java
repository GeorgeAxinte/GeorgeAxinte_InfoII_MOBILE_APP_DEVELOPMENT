package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        result = findViewById(R.id.result);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }

    private void calculate(char operator) {
        String str1 = num1.getText().toString();
        String str2 = num2.getText().toString();

        if (str1.isEmpty() || str2.isEmpty()) {
            result.setText("Alege 2 numere!");
            return;
        }

        double number1 = Double.parseDouble(str1);
        double number2 = Double.parseDouble(str2);
        double res = 0;

        switch (operator) {
            case '+':
                res = number1 + number2;
                break;
            case '-':
                res = number1 - number2;
                break;
            case '*':
                res = number1 * number2;
                break;
            case '/':
                if (number2 != 0) {
                    res = number1 / number2;
                } else {
                    result.setText("Nu poti imparti la 0!");
                    return;
                }
                break;
        }

        result.setText("Rezultat: " + res);
    }
}
