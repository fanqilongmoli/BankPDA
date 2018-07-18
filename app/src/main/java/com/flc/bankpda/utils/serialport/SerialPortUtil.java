package com.flc.bankpda.utils.serialport;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SerialPortServiceManager;
import android.util.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class SerialPortUtil {

    // 参数
    public static final String TAG = "SerialPortUtil";
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

    private byte[] read_buffer = new byte[1024*4];
    private byte[] write_buffer = new byte[1024];

    StringBuffer stringBuffer = new StringBuffer();


    public static int part_display_hex_asc = DISPLAY_READ_ASC;

    //========================//
    Queue<String> rx_queue = new LinkedList<String>();


    private SerialPortServiceManager mSeriport;

    private static SerialPortUtil instance;

    public static SerialPortUtil getInstance() {
        if (instance == null) {
            instance = new SerialPortUtil();
        }
        return instance;
    }

    public void init(Context context) {


        // 取得串口服务；
        // 0--->colse log  1---->open log
        mSeriport = new SerialPortServiceManager(0);

        byte[] part_serialPortNode = "/dev/ttyS2".getBytes();//串口节点
        int part_baud = 9600;// 波特率
        int part_data_size = 8;
        int part_stop_bit = 1;
        // 打开一个串口；
        int ret = mSeriport.open(part_serialPortNode, part_baud, part_data_size, part_stop_bit);

        if (ret > 0) {
            Log.d(TAG, "打开串口操作成功====>" + new String(part_serialPortNode) + "   baud:" + part_baud + "  " + "Button open rturn:" + ret);
        } else {
            Log.d(TAG, "打开串口操作异常====>" + new String(part_serialPortNode) + "   baud:" + part_baud + "  " + "Button open rturn:" + ret);
        }

        Thread mreadTh = new read_thread();


        mreadTh.start();

    }


    public void send(String send) {
        result = null;

        Log.d(TAG, "发送消息====>" + send);
        write_buffer = send.getBytes();
        int ret = mSeriport.write(write_buffer, write_buffer.length);
        if (ret > 0) {
            Log.d(TAG, "发送消息成功====>" + ret);
        } else {
            Log.d(TAG, "发送消息成功====>" + ret);
        }
    }


    // 数据读取线程 ------------------------------------------------------->
    // read serialport data thread
    private byte[] result = null;

    public class read_thread extends Thread {
        private int ret_receive = 0;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                byte[] bytes = "#end#".getBytes();
                if (mSeriport.isReady() > 0) {
                    ret_receive = mSeriport.wait_data();
                    if (ret_receive > 0) {
                        ret_receive = mSeriport.read(read_buffer, 1024*4, 100);// 40ms read
                        if (ret_receive > 0) {
                            Log.e(TAG, "ret_receive==》" + ret_receive);
                            byte[] temp = new byte[ret_receive];

                            for (int i = 0; i < ret_receive; i++) {
                                temp[i] = read_buffer[i];
                            }

                            result = byteMergerAll(result, temp);
                            try {
                                String s = new String(result, 0, result.length, "utf-8");
                                //Log.e(TAG, "读取到的数据==》" + s);

                                if (s.contains("#end#")) {
                                    Log.e(TAG, "end==》" + s);
                                    result = null;
                                }

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
//                try {
//                    Thread.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }


    private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                length_byte += values[i].length;
            }
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            if (values[i] != null) {
                System.arraycopy(b, 0, all_byte, countLength, b.length);
                countLength += b.length;
            }
        }
        return all_byte;
    }

}