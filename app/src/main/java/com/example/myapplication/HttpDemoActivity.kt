package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException


interface ExampleService {
    @GET("api")
    fun getData(
        @Query("unescape") unescape: String,
        @Query("version") version: String,
        @Query("appid") appid: String,
        @Query("appsecret") appsecret: String): Call<Any>
}

class App(val id: String, val name: String)

interface MyappService {

    @GET("/")
    fun getAppData(): Call<Any>
}


class HttpDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_demo)

        val button1: Button = findViewById(R.id.btn1)
        button1.setOnClickListener {
//            val url = "https://v0.yiketianqi.com/api?unescape=1&version=v61&appid=74612742%20&appsecret=8dQmD4dP"
            val url = "https://v0.yiketianqi.com/"
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(ExampleService::class.java)
            appService.getData("1","v61","74612742","8dQmD4dP").enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    val res = response.body()
                    Log.d("test_http", res.toString())
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }
    }
}