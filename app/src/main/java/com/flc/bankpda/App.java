package com.flc.bankpda;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.flc.bankpda.utils.serialport.Gpiocontrol;
import com.flc.bankpda.utils.serialport.SerialPortUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Gpiocontrol.setting();
        SerialPortUtil.getInstance().init(this);
    }
}
