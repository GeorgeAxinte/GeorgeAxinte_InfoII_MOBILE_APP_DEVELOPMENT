package com.example.chatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private String user1, user2, currentUser;
    private EditText editTextMessage;
    private TextView textViewChat, textViewCurrentUser;
    private Button buttonSend, buttonSwitchUser, buttonClearChat;
    private ArrayList<String> chatMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Primim numele utilizatorilor din intent
        user1 = getIntent().getStringExtra("user1");
        user2 = getIntent().getStringExtra("user2");
        currentUser = user1; // Începem cu User 1

        editTextMessage = findViewById(R.id.editTextMessage);
        textViewChat = findViewById(R.id.textViewChat);
        textViewCurrentUser = findViewById(R.id.textViewCurrentUser);
        buttonSend = findViewById(R.id.buttonSend);
        buttonSwitchUser = findViewById(R.id.buttonSwitchUser);
        buttonClearChat = findViewById(R.id.buttonClearChat);

        updateCurrentUserDisplay();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        buttonSwitchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchUser();
            }
        });

        buttonClearChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChat();
            }
        });
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            chatMessages.add(currentUser + ": " + message);
            updateChatDisplay();
            editTextMessage.setText("");
        }
    }

    private void switchUser() {
        currentUser = currentUser.equals(user1) ? user2 : user1;
        updateCurrentUserDisplay();
    }

    private void updateCurrentUserDisplay() {
        textViewCurrentUser.setText("User curent: " + currentUser);
    }

    private void updateChatDisplay() {
        StringBuilder chatText = new StringBuilder();
        for (String msg : chatMessages) {
            chatText.append(msg).append("\n");
        }
        textViewChat.setText(chatText.toString());
    }

    private void clearChat() {
        chatMessages.clear();
        updateChatDisplay();
    }
}
