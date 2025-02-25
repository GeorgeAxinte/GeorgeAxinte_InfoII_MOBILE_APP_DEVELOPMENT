package com.example.click;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMessage;
    private Button buttonClickMe;
    private boolean toggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessage = findViewById(R.id.textViewMessage);
        buttonClickMe = findViewById(R.id.buttonClickMe);

        buttonClickMe.setOnClickListener(view -> {
            if (!toggled) {
                textViewMessage.setText("Message1!");
                toggled = true;
            } else {
                textViewMessage.setText("Message2!");
                toggled = false;
            }
        });
    }
}
