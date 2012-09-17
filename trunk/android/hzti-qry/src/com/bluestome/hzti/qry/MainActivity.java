package com.bluestome.hzti.qry;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bluestome.hzti.qry.net.QueryTools;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends Activity implements OnClickListener{
	
	private Spinner spinner = null;
	private Button btnOk;
	private Button btnCancel;
	
	// 车牌号码
	private EditText textCarNum;
	// 车辆识别码
	private EditText textCarId;
	// 校验码
	private EditText textCheckCode;
	
	private String carType = "小型汽车";
	
	private ImageView imageView = null;
	
	public static String AUTH_CODE_URL = "http://www.hzti.com/government/CreateCheckCode.aspx?"+System.currentTimeMillis();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
    }
    
    /**
     * 初始化UI
     */
    private void initUI(){
        // 获取spinner
        spinner = (Spinner)findViewById(R.id.spinner1);
        // 从资源中获取数组数据
        String[] array = getResources().getStringArray(R.array.select_car_type);
        ArrayAdapter<String> array_adapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item,array);
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array_adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				carType=parent.getItemAtPosition(position).toString();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
        
        // 车牌号码
        textCarNum = (EditText)findViewById(R.id.e_car_license_num);
        textCarNum.setText("浙A");
        
        // 车辆识别码
        textCarId = (EditText)findViewById(R.id.e_car_id);
        
        // 校验码
        textCheckCode = (EditText)findViewById(R.id.e_check_code);
        
        // 校验码图片
        imageView = (ImageView)findViewById(R.id.checkCodeView);
        imageView.setOnClickListener(this);
        
        
        loadImage(AUTH_CODE_URL,R.id.checkCodeView);
        
        // 初始化按钮
        btnOk = (Button)findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        
        btnCancel = (Button)findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch(vid){
			case R.id.btn_cancel:
			    finish();
				break;
			case R.id.btn_ok:
				//获取所有输入的参数
				final String carNum = textCarNum.getText().toString().trim();
				if(carNum.equals("") || carNum.length() < 7){
					Toast.makeText(this, getString(R.string.tip_car_num), 2000).show();
					return;
				}
				final String carId = textCarId.getText().toString().trim();
				if(carId.equals("") || carId.length() < 6){
					Toast.makeText(this, getString(R.string.tip_car_id), 2000).show();
					return;
				}
				final String checkCode = textCheckCode.getText().toString().trim();
				if(checkCode.equals("") || checkCode.length() < 4){
					Toast.makeText(this, getString(R.string.tip_check_code), 2000).show();
					return;
				}
				Toast.makeText(this, carNum+"|"+carId+"|"+checkCode+"|"+carType, 3000).show();
				new Thread(new Runnable(){
					public void run(){
						QueryTools.doQuery(carId, carNum, carId, checkCode);
					}
				}).start();
				//TODO 将参数传入另一个界面处理网络相关
//				startActivity(new Intent(this,GridShowActivity.class));
				break;
			case R.id.checkCodeView:
			    loadImage(AUTH_CODE_URL,R.id.checkCodeView);
			    break;
		}
	}
	
	final Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ((ImageView) MainActivity.this.findViewById(msg.arg1))
                    .setImageDrawable((Drawable) msg.obj);
        }
    };
    
    /**
	 * 获取校验码图片
	 */
	private void loadImage(final String url, final int id) {
        new Thread(new Runnable() {
            public void run() {
                Drawable drawable = null;
                try {
                    drawable = Drawable.createFromStream(
                            new URL(url).openStream(), "image.png");
                } catch (IOException e) {
                    Log.d("test", e.getMessage());
                }
                Message message = handler2.obtainMessage();
                message.arg1 = id;
                message.obj = drawable;
                handler2.sendMessage(message);
            }
        }).start();
    }}