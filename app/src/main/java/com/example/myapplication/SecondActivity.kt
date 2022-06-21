package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

//        val editText: EditText = findViewById(R.id.editText)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button2 -> {
                Log.d("test", "dddddd")
                val editText: EditText = findViewById(R.id.editText)
                val inputText = editText.text.toString()
                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
                val intent = Intent("com.example.myapplication.MY_BROADCAST")
                intent.setPackage(packageName)
                sendBroadcast(intent)
            }
            R.id.button3 -> {
               AlertDialog.Builder(this).apply {
                   setTitle("this is a dialog")
                   setMessage("this is important message")
                   setCancelable(false)
                   setPositiveButton("OK"){ _, _ ->}
                   setPositiveButton("Cancel"){ _, _ ->}
                   show()
               }
            }
        }
    }
}