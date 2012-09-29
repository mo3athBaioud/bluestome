
package com.example.devinfodemo.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.devinfodemo.R;
import com.example.devinfodemo.R.id;
import com.example.devinfodemo.R.layout;

import java.util.List;

/**
 * @ClassName: SensorActivity
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-9-28 上午11:29:35
 */
public class SensorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_main);

        // 准备显示信息的UI组建
        final TextView tx1 = (TextView) findViewById(R.id.TextView01);

        // 从系统服务中获得传感器管理器
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 从传感器管理器中获得全部的传感器列表
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);

        // 显示有多少个传感器
        tx1.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");

        StringBuilder sb = new StringBuilder();
        // 显示每个传感器的具体信息
        for (Sensor s : allSensors) {

            String tempString = "\n" + "  设备名称：" + s.getName() + "\n" +
                    "  设备版本：" + s.getVersion()
                    + "\n" + "  供应商："
                    + s.getVendor() + "\n";
            // sb.append(s.getName()).append("|").append(s.getType()).append(",");

            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 加速度传感器accelerometer"
                            + tempString);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 陀螺仪传感器gyroscope"
                            + tempString);
                    break;
                case Sensor.TYPE_LIGHT:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 环境光线传感器light"
                            + tempString);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 电磁场传感器magnetic field"
                            + tempString);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 方向传感器orientation"
                            + tempString);
                    break;
                case Sensor.TYPE_PRESSURE:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 压力传感器pressure"
                            + tempString);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 距离传感器proximity"
                            + tempString);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 温度传感器temperature"
                            + tempString);
                    break;
                default:
                    tx1.setText(tx1.getText().toString() + s.getType() +
                            " 未知传感器" + tempString);
                    tx1.setText(tx1.getText().toString() + s.getType() + tempString);
                    break;
            }
        }
        // String str = sb.toString();
        // if (str.endsWith(",")) {
        // str = str.substring(0, str.length() - 1);
        // }
        // tx1.setText(tx1.getText() + "\n" + str.length() + "\n" + str);

    }

}
