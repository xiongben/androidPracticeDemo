package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.myapplication.ui.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStartBAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Bty.class);
//                i.putExtra("data","hello,this is params data");
                i.putExtra("user", new User("xiaoming",12));
                startActivity(i);
            }
        });


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