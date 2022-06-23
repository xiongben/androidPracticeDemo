package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter


class SecondActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

//        val editText: EditText = findViewById(R.id.editText)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        save()
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
//               AlertDialog.Builder(this).apply {
//                   setTitle("this is a dialog")
//                   setMessage("this is important message")
//                   setCancelable(false)
//                   setPositiveButton("OK"){ _, _ ->}
//                   setPositiveButton("Cancel"){ _, _ ->}
//                   show()
//               }

//                打电话权限demo
                 if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                     ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                 } else {
                     call()
                 }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call()
                } else {
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call () {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun save() {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write("this is san niu test document")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}