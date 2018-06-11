package com.flc.bankpda.utils.gpiocontrol;

import java.io.File;
import java.io.FileWriter;

public class Gpiocontrol {

    String PSAM_POWER_PATH = "/proc/gpiocontrol/set_sam";
    String RFID_POWER_PATH = "/proc/gpiocontrol/set_id";
    String SCAN_POWER_PATH = "/sys/bus/platform/devices/physical-keypad.0/open";
    String UHF_POWER_PATH = "/proc/gpiocontrol/set_uhf";

    public void gpio_control(String paramString, int paramInt){

        if(paramInt == 0 || paramInt == 1){
            try {
                FileWriter fileWriter = new FileWriter(new File(paramString));
                if (paramInt == 1){
                    fileWriter.write("1");
                }else {
                    fileWriter.write("0");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
