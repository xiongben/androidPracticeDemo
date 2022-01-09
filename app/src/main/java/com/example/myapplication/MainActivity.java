package com.example.myapplication;

import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.myapplication.ui.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
    private TextView textView;
    private EditText etData;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("service connected =====");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("service no connected =====");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);

        etData = findViewById(R.id.etData);

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

        //server启动
        findViewById(R.id.buttonStartServers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                i.putExtra("data", etData.getText().toString());
                startService(i);
            }
        });

        findViewById(R.id.buttonStopServers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        });

        findViewById(R.id.buttonBindServers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bindService(new Intent(MainActivity.this, MyService.class), conn, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.buttonUnbindServers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               unbindService(conn);
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