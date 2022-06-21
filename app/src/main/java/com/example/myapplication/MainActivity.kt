package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

//    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        button1.setOnClickListener{
//            Toast.makeText(this, "you click button1", Toast.LENGTH_SHORT).show()

            // 显式intend
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

//            // 隐式intend
//            val intent = Intent("com.example.myapplication.ACTION_START")
//            intent.addCategory("com.example.myapplication.MY_CATEGORY")
//            startActivity(intent)

//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")
//            startActivity(intent)
        }

        button2.setOnClickListener {
//            val intent = Intent(this, RecyclerView::class.java)
            val intent = Intent(this, FragmentDemo::class.java)
            startActivity(intent)
        }

        // 广播
//        val intentFilter = IntentFilter()
//        intentFilter.addAction("android.intent.action.TIME_TICK")
//        timeChangeReceiver = TimeChangeReceiver()
//        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(timeChangeReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item ->  Toast.makeText(this, "you click add", Toast.LENGTH_SHORT).show()
            R.id.remove_item ->  Toast.makeText(this, "you click remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    inner class TimeChangeReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }
    }


}

class MyBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show()
    }
}