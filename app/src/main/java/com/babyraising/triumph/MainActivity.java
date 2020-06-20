package com.babyraising.triumph;

import android.serialport.SerialPort;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android_serialport_api.SerialPortFinder;
import top.keepempty.sph.library.DataConversion;
import top.keepempty.sph.library.SerialPortHelper;
import top.keepempty.sph.library.SphCmdEntity;
import top.keepempty.sph.library.SphResultCallback;
import tp.xmaihh.serialport.SerialHelper;
import tp.xmaihh.serialport.bean.ComBean;

public class MainActivity extends AppCompatActivity {

    private String TAG = "testSerial";

    private SerialPortFinder serialPortFinder;
    private SerialHelper serialHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test();
//        testSend();
        testAnother();
    }

    private void testAnother() {
        SerialPortHelper serialPortHelper = new SerialPortHelper(32, true);
        serialPortHelper.openDevice("dev/ttyS0", 115200);
        // 数据接收回调
        serialPortHelper.setSphResultCallback(new SphResultCallback() {
            @Override
            public void onSendData(SphCmdEntity sendCom) {
                Log.d(TAG, "发送命令：" + sendCom.commandsHex);
//                System.out.println(new String(sendCom.commandsHex));
            }

            @Override
            public void onReceiveData(SphCmdEntity data) {
                Log.d(TAG, "收到命令：" + data.commandsHex);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "完成");
            }
        });
        byte[] commands = "".getBytes();
        // 发送数据实体
        SphCmdEntity comEntry = new SphCmdEntity();
        comEntry.commands = commands; // 发送命令字节数组
        comEntry.flag = 2222;         // 备用标识
        comEntry.commandsHex = DataConversion.encodeHexString(commands);  // 发送十六进制字符串
        comEntry.timeOut = 100;       // 超时时间 ms
        comEntry.reWriteCom = false;  // 超时是否重发 默认false
        comEntry.reWriteTimes = 5;    // 重发次数
        comEntry.receiveCount = 1;    // 接收数据条数，默认为1
        serialPortHelper.addCommands(comEntry);
    }

    private void testSend() {
        try {
            serialHelper.setPort("/dev/ttyS1");
            serialHelper.setBaudRate(115200);
            serialHelper.open();
            System.out.println(serialHelper.getPort());
            System.out.println(serialHelper.getBaudRate());
//            serialHelper.send("aaaaaa".getBytes()); // 发送byte[]
//            serialHelper.sendHex("123456");  // 发送Hex
            serialHelper.sendHex("0000000");  // 发送ASCII
//            serialHelper.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("串口打开异常:" + e.toString());
        }
    }

    private void test() {
        serialPortFinder = new SerialPortFinder();
        for (int i = 0; i < serialPortFinder.getAllDevicesPath().length; i++) {
            System.out.println(serialPortFinder.getAllDevicesPath()[i]);
        }
        serialHelper = new SerialHelper("/dev/ttyS1", 115200) {
            @Override
            protected void onDataReceived(final ComBean comBean) {
                System.out.println("onDataReceived");
                try {
                    System.out.println(new String(comBean.bRec, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("输出解析:" + e.toString());
                }
            }
        };
    }


}
