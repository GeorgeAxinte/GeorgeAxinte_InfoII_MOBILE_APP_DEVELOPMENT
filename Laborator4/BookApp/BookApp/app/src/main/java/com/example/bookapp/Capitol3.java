package com.example.bookapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Capitol3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capitol3, container, false);

        Button btnNext = view.findViewById(R.id.btnNext);
        Button btnPrev = view.findViewById(R.id.btnPrev);

        btnNext.setOnClickListener(v -> loadFragment(new Capitol4()));
        btnPrev.setOnClickListener(v -> loadFragment(new Capitol2()));

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
