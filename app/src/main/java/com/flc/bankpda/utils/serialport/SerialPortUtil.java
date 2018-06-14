package com.flc.bankpda.utils.serialport;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class SerialPortUtil {

    // 参数
    public static final String TAG = "Serialport-APP";
    private static final int DISPLAY_READ_HEX = 0x000;
    private static final int DISPLAY_READ_ASC = 0x001;
    private static final int DISPLAY_READ_HEX_F = 0x002;
    private static final int DISPLAY_READ_ASC_F = 0x003;
    private static final int DISPLAY_WRITE_HEX = 0x010;
    private static final int DISPLAY_WRITE_ASC = 0x011;
    private static final int ACTION_SCREEN_ON = 0x100;
    private static final int ACTION_SCREEN_OFF = 0x101;
    private static final int ACTION_MESSAGE_ERROR = 0xfff;
    private static final int Message_display = 0x010;
    private static final int TX_display = 0x011;

    // cmd index
    public static final int cmd_input = 0x2;
    public static final int cmd_out = 0x3;
    public static final int gps_power_off = 0x100;
    public static final int gps_power_on = 0x101;
    public String power_cmd[] = new String[4];
    public String power_path[] = new String[32];

    // find node
    Vector<File> mDevices = null;
    // 接收到多少时clean（fast模式时）
    public static final int rx_max = 10000;
    // 读显 锁
    private static Object rxlock = new Object();

    // SD卡路径
    // private static String sd_dir = "/mnt/sdcard/external_sdcard/SerialportLog/";
    private static String sd_dir = "/storage/sdcard1/SerialportLog/";
    private static File file;
    private static int sd_state_flag = 0;
    private static boolean enableMKDir = false;


    String RFID_POWER_PATH = "/proc/gpiocontrol/set_id";
    String SCAN_EN_PATH = "/proc/gpiocontrol/set_en";
    String PSAM_POWER_PATH = "/proc/gpiocontrol/set_sam";
    // 写入参数
    public static byte[] part_serialPortNode = null;
    public static int part_baud = 0;
    public static int part_data_size = 8;
    public static int part_stop_bit = 1;
    public static int part_display_hex_asc = DISPLAY_READ_ASC;
    private Handler mHandler = null;

    // 查找串口相关
    public List<String> allNode;

    //========================//

    private SerialPortUtil instance;

    public SerialPortUtil getInstance() {
        if (instance == null) {
            instance = new SerialPortUtil();
        }
        return instance;
    }

    public void init(Context context) {

    }
}
