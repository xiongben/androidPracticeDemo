package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Bty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bty);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("b onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("b onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("b onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("b onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("b onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("b onRestart");
    }
}