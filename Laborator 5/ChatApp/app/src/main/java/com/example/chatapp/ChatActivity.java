package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    private TextView textViewCurrentUser, textViewChat;
    private EditText editTextMessage;
    private Button buttonSend, buttonSwitchUser, buttonClearChat;

    private String user1, user2, currentUser;
    private StringBuilder chatHistory = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        textViewCurrentUser = findViewById(R.id.textViewCurrentUser);
        textViewChat = findViewById(R.id.textViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        buttonSwitchUser = findViewById(R.id.buttonSwitchUser);
        buttonClearChat = findViewById(R.id.buttonClearChat);

        Intent intent = getIntent();
        user1 = intent.getStringExtra("USER_1");
        user2 = intent.getStringExtra("USER_2");
        currentUser = intent.getStringExtra("CURRENT_USER");
        String chatHistoryFromIntent = intent.getStringExtra("CHAT_HISTORY");

        if (chatHistoryFromIntent != null) {
            chatHistory.append(chatHistoryFromIntent);
            textViewChat.setText(chatHistory.toString());
        }

        updateUserDisplay();

        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                chatHistory.append(currentUser).append(": ").append(message).append("\n");
                textViewChat.setText(chatHistory.toString());
                editTextMessage.setText("");
            }
        });

        buttonSwitchUser.setOnClickListener(v -> switchUser());

        buttonClearChat.setOnClickListener(v -> {
            chatHistory.setLength(0);
            textViewChat.setText("");
        });
    }


    private void switchUser() {
        Intent intent = new Intent(ChatActivity.this, ChatActivity.class);
        intent.putExtra("USER_1", user1);
        intent.putExtra("USER_2", user2);
        intent.putExtra("CURRENT_USER", currentUser.equals(user1) ? user2 : user1);
        intent.putExtra("CHAT_HISTORY", chatHistory.toString());
        startActivity(intent);
        finish();
    }


    private void updateUserDisplay() {
        textViewCurrentUser.setText("User curent: " + currentUser);
    }
}
