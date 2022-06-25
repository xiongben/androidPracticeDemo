package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
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

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d("MyService", "onServiceDisconnected")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)

        val changeTextBtn: Button = findViewById(R.id.changeTextBtn)
        val startServiceBtn: Button = findViewById(R.id.startServiceBtn)
        val stopServiceBtn: Button = findViewById(R.id.stopServiceBtn)
        val bindServiceBtn: Button = findViewById(R.id.bindServiceBtn)
        val unbindServiceBtn: Button = findViewById(R.id.unbindServiceBtn)


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

        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service
        }
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
    }
}