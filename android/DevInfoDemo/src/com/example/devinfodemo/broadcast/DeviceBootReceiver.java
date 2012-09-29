
package com.example.devinfodemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.devinfodemo.service.DeviceService;

/**
 * @ClassName: DeviceBootReceiver
 * @Description: 启动手机时同时启动手信service
 * @author Michael.Pan
 * @date 2012-2-22 下午02:39:42
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    private static final String TAG = DeviceBootReceiver.class.getSimpleName();
    private static final int MSG_DELAY_START_SERVICE = 0x01;
    private static final int DELAY_MILLIS = 3000;// 3000ms

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_DELAY_START_SERVICE:
                    Context context = (Context) msg.obj;
                    Intent IntentService = new Intent(context, DeviceService.class);
                    context.startService(IntentService);
                    break;
            }
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.e(TAG, "DeviceBootReceiver broadcast is receiver");
            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_DELAY_START_SERVICE, context),
                    DELAY_MILLIS);
        }
    }
}
