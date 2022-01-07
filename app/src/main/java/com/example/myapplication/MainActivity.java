package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.myapplication.ui.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);

        textView.setText(String.format("taskId:%d,current activity id:%s",getTaskId(),toString()));

        findViewById(R.id.btnStartBAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Bty.class);
//                i.putExtra("data","hello,this is params data");
                i.putExtra("user", new User("xiaoming",12));
//                startActivity(i);
                startActivityForResult(i,0);
            }
        });

        //显示和隐式intent
        findViewById(R.id.buttonStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MyAty.class));
                try {
                    startActivity(new Intent("com.example.myapplication.intent.action.MyAty"));
                }catch (ActivityNotFoundException a) {
                    a.getMessage();
                }

            }
        });

//        //获取全局application
//        getApplicationContext();


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textView.setText(data.getStringExtra("data"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("main onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("main onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("main onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("main onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("main onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("main onRestart");
    }

}