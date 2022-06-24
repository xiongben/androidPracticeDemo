package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread


class ServiceDemo : AppCompatActivity() {

    val updateText = 1

    val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val textView: TextView = findViewById(R.id.textView)
            when(msg.what) {
                updateText -> textView.text = "Nice to meet you"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)

        val changeTextBtn: Button = findViewById(R.id.changeTextBtn)
        val startServiceBtn: Button = findViewById(R.id.startServiceBtn)
        val stopServiceBtn: Button = findViewById(R.id.stopServiceBtn)

        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }

        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
    }
}