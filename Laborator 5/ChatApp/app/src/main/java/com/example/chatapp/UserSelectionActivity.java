package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserSelectionActivity extends AppCompatActivity {
    private EditText editTextUser1, editTextUser2;
    private Button buttonStartChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        editTextUser1 = findViewById(R.id.editTextUser1);
        editTextUser2 = findViewById(R.id.editTextUser2);
        buttonStartChat = findViewById(R.id.buttonStartChat);

        buttonStartChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = editTextUser1.getText().toString().trim();
                String user2 = editTextUser2.getText().toString().trim();

                if (!user1.isEmpty() && !user2.isEmpty()) {
                    Intent intent = new Intent(UserSelectionActivity.this, ChatActivity.class);
                    intent.putExtra("user1", user1);
                    intent.putExtra("user2", user2);
                    startActivity(intent);
                }
            }
        });
    }
}
