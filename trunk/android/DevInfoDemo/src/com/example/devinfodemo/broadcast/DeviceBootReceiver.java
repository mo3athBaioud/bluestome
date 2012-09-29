
package com.example.devinfodemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @ClassName: DeviceBootReceiver
 * @Description: 启动手机时同时启动手信service
 * @author Michael.Pan
 * @date 2012-2-22 下午02:39:42
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "启动完成", Toast.LENGTH_LONG);
        }
    }
}
