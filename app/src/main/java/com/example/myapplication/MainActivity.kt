package com.example.myapplication

import android.content.*
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
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
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

        button3.setOnClickListener {
            dbHelper.writableDatabase
        }

        button4.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据
        }

        button5.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            val rows = db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
            Toast.makeText(this, "rows is $rows", Toast.LENGTH_SHORT).show()
        }

        // 广播
//        val intentFilter = IntentFilter()
//        intentFilter.addAction("android.intent.action.TIME_TICK")
//        timeChangeReceiver = TimeChangeReceiver()
//        registerReceiver(timeChangeReceiver, intentFilter)

        button6.setOnClickListener {
            val intent = Intent(this, ServiceDemo::class.java)
            startActivity(intent)
        }
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