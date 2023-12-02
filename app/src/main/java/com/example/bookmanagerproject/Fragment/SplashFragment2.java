package com.example.bookmanagerproject.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bookmanagerproject.Login;
import com.example.bookmanagerproject.MainActivity;
import com.example.bookmanagerproject.R;

public class SplashFragment2 extends Fragment {
    private View view;
    private Button btnBatDau;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_splash2, container, false);
        btnBatDau = view.findViewById(R.id.bntBatDau);
        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}