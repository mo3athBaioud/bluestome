
package com.example.devinfodemo.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.TextView;

import com.example.devinfodemo.R;
import com.example.devinfodemo.R.id;
import com.example.devinfodemo.R.layout;

/**
 * 要照顾到低版本的手机系统
 * 
 * @ClassName: CameraActivity
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-9-28 上午11:30:56
 */
@TargetApi(9)
public class CameraActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_main);

        // 准备显示信息的UI组建
        final TextView tx1 = (TextView) findViewById(R.id.TextView01);

        int cameraCount = 0;

        /**
         * 三星 手机会崩溃 摩托ME860会崩溃 可能是因为TargetApi的最低版本不符合导致
         */
        cameraCount = Camera.getNumberOfCameras();
        tx1.setText("摄像头数量为：" + cameraCount);
    }

}
