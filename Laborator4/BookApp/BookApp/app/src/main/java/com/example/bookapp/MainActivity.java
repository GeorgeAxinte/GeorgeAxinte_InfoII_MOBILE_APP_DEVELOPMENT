package com.example.bookapp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCap1 = findViewById(R.id.btnToChapter1);
        Button btnCap2 = findViewById(R.id.btnToChapter2);
        Button btnCap3 = findViewById(R.id.btnToChapter3);
        Button btnCap4 = findViewById(R.id.btnToChapter4);

        btnCap1.setOnClickListener(v -> loadFragment(new Capitol1()));
        btnCap2.setOnClickListener(v -> loadFragment(new Capitol2()));
        btnCap3.setOnClickListener(v -> loadFragment(new Capitol3()));
        btnCap4.setOnClickListener(v -> loadFragment(new Capitol4()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
