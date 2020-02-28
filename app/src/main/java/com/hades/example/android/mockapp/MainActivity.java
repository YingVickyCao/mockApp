package com.hades.example.android.mockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hades.example.android.mockapp.price.Type1;
import com.hades.example.android.mockapp.price.Type2;

public class MainActivity extends AppCompatActivity {
    Type1 mType1 = new Type1();
    Type2 mType2 = new Type2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.newSession1).setOnClickListener(v -> newSession1());
        findViewById(R.id.newSession2).setOnClickListener(v -> newSession2());
        findViewById(R.id.clearSession1).setOnClickListener(v -> clearSession1());
        findViewById(R.id.clearSession2).setOnClickListener(v -> clearSession2());
    }

    private void newSession1() {
        mType1.getSessionToken();
    }

    private void newSession2() {
        mType2.getSessionToken();
    }

    private void clearSession1() {
        mType1.clearSessionData();
    }

    private void clearSession2() {
        mType2.clearSessionData();
    }
}
