package com.example.myapplication;

import android.app.Application;
import android.content.res.Configuration;
import androidx.annotation.NonNull;

public class App extends Application {
    private String textData = "xiongben";

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("app onCreat===");
    }

    //模拟条件下执行
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    //内存清理的时候执行
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
