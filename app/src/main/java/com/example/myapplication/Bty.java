package com.example.myapplication;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.ui.User;

public class Bty extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bty);

        Intent i = getIntent(); //接收参数
        tv = (TextView) findViewById(R.id.tv);
//        tv.setText(i.getStringExtra("data "));
        User user = (User) i.getSerializableExtra("user");
        tv.setText(String.format("user info(name=%s,age=%d)",user.getName(),user.getAge()));
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