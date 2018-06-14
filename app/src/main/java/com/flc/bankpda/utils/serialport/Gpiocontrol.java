package com.flc.bankpda.utils.serialport;

import java.io.File;
import java.io.FileWriter;

public class Gpiocontrol {

    static String PSAM_POWER_PATH = "/proc/gpiocontrol/set_sam";
    static String RFID_POWER_PATH = "/proc/gpiocontrol/set_id";
    static String SCAN_POWER_PATH = "/sys/bus/platform/devices/physical-keypad.0/open";
    static String UHF_POWER_PATH = "/proc/gpiocontrol/set_uhf";

    public static void  setting() {
        gpioControl(SCAN_POWER_PATH, 1);
        gpioControl(RFID_POWER_PATH, 1);
        gpioControl(PSAM_POWER_PATH, 1);
        gpioControl(UHF_POWER_PATH, 1);
    }

    private static void gpioControl(String paramString, int paramInt) {

        if (paramInt == 0 || paramInt == 1) {
            try {
                FileWriter fileWriter = new FileWriter(new File(paramString));
                if (paramInt == 1) {
                    fileWriter.write("1");
                } else {
                    fileWriter.write("0");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
