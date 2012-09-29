
package com.example.devinfodemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.devinfodemo.R;
import com.example.devinfodemo.R.id;
import com.example.devinfodemo.R.layout;
import com.example.devinfodemo.R.menu;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
            default:
                Toast.makeText(this, "等待开发...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
