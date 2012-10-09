
package com.example.devinfodemo.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.devinfodemo.R;
import com.example.devinfodemo.json.bean.Hardware;
import com.example.devinfodemo.json.bean.Phone;
import com.example.devinfodemo.json.bean.Software;
import com.example.devinfodemo.json.bean.hd.JSDInfo;
import com.example.devinfodemo.json.bean.hd.JSensor;
import com.example.devinfodemo.json.bean.hd.JSensorDetail;
import com.example.devinfodemo.json.bean.hd.PhoneNetworkInfo;
import com.example.devinfodemo.json.bean.sd.DatabaseInfo;
import com.example.devinfodemo.service.DeviceService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener {
    private final static String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 启动服务
        Intent intentService = new Intent(this, DeviceService.class);
        startService(intentService);

        DeviceService.getInstance();

        Button btnSersor = (Button) findViewById(R.id.btn_sensor);
        btnSersor.setOnClickListener(this);

        Button btnCamera = (Button) findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(this);

        Button btnMultiSim = (Button) findViewById(R.id.btn_multi_sim);
        btnMultiSim.setOnClickListener(this);

        Button btnNetwork = (Button) findViewById(R.id.btn_network);
        btnNetwork.setOnClickListener(this);

        Button btnAbout = (Button) findViewById(R.id.btn_abount);
        btnAbout.setOnClickListener(this);

        Button btnOther = (Button) findViewById(R.id.btn_other);
        btnOther.setOnClickListener(this);

        Button jsonBtn = (Button) findViewById(R.id.btn_json);
        jsonBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sensor:
                startActivity(new Intent(this, SensorActivity.class));
                break;
            case R.id.btn_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.btn_multi_sim:
                startActivity(new Intent(this, MultiSimActivity.class));
                break;
            case R.id.btn_network:
                startActivity(new Intent(this, NetworkActivity.class));
                break;
            case R.id.btn_abount:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.btn_json:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            initJson();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(this, "正在生成json...", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "等待开发...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private Context getContext() {
        return this;
    }

    /**
     * 
     */
    private void initJson() throws JSONException {

        // 系统版本号
        int version = android.os.Build.VERSION.SDK_INT;

        Phone phone = new Phone();
        JSONObject jhd = new JSONObject();
        Hardware hd = new Hardware();
        // 摄像头
        if (version > 8) {
            int cameraNum = getCameraNum();
            hd.setCameraNum(cameraNum);
            jhd.put("camera", cameraNum);
        }
        TelephonyManager tm = (TelephonyManager) getContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        // IMSI
        if (null != tm.getSubscriberId()) {
            hd.setImsi(tm.getSubscriberId());
        }
        // IMEI
        if (null != tm.getDeviceId()) {
            hd.setImei(tm.getDeviceId());
        }

        // 传感器
        // 从系统服务中获得传感器管理器
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 从传感器管理器中获得全部的传感器列表
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);
        JSensor sensor = new JSensor();
        sensor.setTotal(allSensors.size());
        // 显示每个传感器的具体信息
        for (Sensor s : allSensors) {
            JSensorDetail detail = new JSensorDetail();
            detail.setTypeId(s.getType());
            detail.setTypeName(s.getName());
            detail.setTypeVersion(String.valueOf(s.getVersion()));
            sensor.getList().add(detail);
        }
        hd.setSersor(sensor);

        // 网络
        ConnectivityManager mgr = (ConnectivityManager) getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mgr.getActiveNetworkInfo();
        PhoneNetworkInfo network = new PhoneNetworkInfo();
        if (null != networkInfo) {
            int type = networkInfo.getType();
            switch (type) {
                case ConnectivityManager.TYPE_MOBILE:
                    network.setActiviteNetwork("TYPE_MOBILE");
                    break;
                case ConnectivityManager.TYPE_WIFI:
                    network.setActiviteNetwork("TYPE_WIFI");
                    break;
                case ConnectivityManager.TYPE_WIMAX:
                    network.setActiviteNetwork("TYPE_WIMAX");
                    break;
                default:
                    network.setActiviteNetwork("TYPE_" + type);
                    break;
            }
            String networkType = getPhoneNetworkType(getContext());
            network.setPhoneNetworkType(networkType);
        } else {
            network.setPhoneNetworkType(PhoneNetworkInfo.TYPE_UNKNOW);
            network.setActiviteNetwork(PhoneNetworkInfo.TYPE_UNKNOW);
        }
        hd.setNetwork(network);

        // SD卡
        long[] sds = getSDCardMemory();
        JSDInfo sdi = new JSDInfo();
        sdi.setTotalSize(String.valueOf(formatSize(sds[0])));
        sdi.setAvailableSize(String.valueOf(formatSize(sds[1])));
        sdi.setInternalMemorySize(String.valueOf(formatSize(getTotalInternalMemorySize())));
        hd.setSdInfo(sdi);

        phone.setHardware(hd);
        Software sd = new Software();
        // 数据库信息
        DatabaseInfo db = new DatabaseInfo();
        sd.setDatabas(db);

        // 系统版本号
        sd.setSysVersion(String.valueOf(version));

        // 厂商
        sd.setHsman(android.os.Build.MANUFACTURER);

        // 机型
        sd.setHstype(android.os.Build.MODEL);

        // 屏幕大小
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wmgr = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wmgr.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        sd.setScreenSize(screenWidth + "x" + screenHeight);

        phone.setSoftware(sd);
        // JSONObject json;
        Gson gson = new Gson();
        phone.setIp("172.168.1.110");
        String json = gson.toJson(phone);
        Log.d(TAG, "JSON长度:" + json.length());
        Log.d(TAG, json.toString());
    }

    @TargetApi(9)
    private int getCameraNum() {
        int cameraCount = 0;
        /**
         * 三星 手机会崩溃 摩托ME860会崩溃 可能是因为TargetApi的最低版本不符合导致
         */
        cameraCount = Camera.getNumberOfCameras();

        return cameraCount;
    }

    /**
     * 获取手机网络类型
     * 
     * @param context
     * @return
     */
    private String getPhoneNetworkType(Context context) {
        String networkType = "";
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        int type = tm.getNetworkType();
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_CDMA:
                networkType = "NETWORK_TYPE_CDMA";
                break;
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                networkType = "NETWORK_TYPE_1xRTT";
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                networkType = "NETWORK_TYPE_EDGE";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                networkType = "NETWORK_TYPE_EVDO_0";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                networkType = "NETWORK_TYPE_EVDO_A";
                break;
            // case TelephonyManager.NETWORK_TYPE_EVDO_B:
            // networkType = "NETWORK_TYPE_EVDO_B";
            // break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                networkType = "NETWORK_TYPE_GPRS";
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                networkType = "NETWORK_TYPE_HSDPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                networkType = "NETWORK_TYPE_HSPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                networkType = "NETWORK_TYPE_HSUPA";
                break;
            // case TelephonyManager.NETWORK_TYPE_IDEN:
            // networkType = "NETWORK_TYPE_IDEN";
            // break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                networkType = "NETWORK_TYPE_UMTS";
                break;
            default:
                networkType = "NETWORK_TYPE_UNKNOWN";
                break;
        }
        return networkType;
    }

    /**
     * SD卡存储大小
     * 
     * @return
     */
    public long[] getSDCardMemory() {
        long[] sdCardInfo = new long[2];
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long bSize = sf.getBlockSize();
            long bCount = sf.getBlockCount();
            long availBlocks = sf.getAvailableBlocks();

            sdCardInfo[0] = bSize * bCount;// 总大小
            sdCardInfo[1] = bSize * availBlocks;// 可用大小
        }
        return sdCardInfo;
    }

    /**
     * 内部存储大小
     * 
     * @return
     */
    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 格式化数据的方法
     * 
     * @param size
     * @return
     */
    public String formatSize(long size) {
        String suffix = null;
        float fSize = 0;

        if (size >= 1024) {
            suffix = "KB";
            fSize = size / 1024;
            if (fSize >= 1024) {
                suffix = "MB";
                fSize /= 1024;
            }
            if (fSize >= 1024) {
                suffix = "GB";
                fSize /= 1024;
            }
        } else {
            fSize = size;
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

}
