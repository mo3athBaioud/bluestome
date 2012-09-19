package com.bluestome.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bluestome.asynctask.task.DownloadTask;
import com.bluestome.asynctask.task.UrlLoadTask;

public class MainActivity extends Activity {
        
    Button download;
    ProgressBar pb;
    TextView tv;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=(ProgressBar)findViewById(R.id.pb);
        tv=(TextView)findViewById(R.id.tv);
        
        download = (Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
//                DownloadTask dTask = new DownloadTask(MainActivity.this,download,tv,pb);
//                dTask.execute(1000,200,250);
                //将按钮设置为不可用
                download.setEnabled(false);
                
                UrlLoadTask taks = new UrlLoadTask(MainActivity.this,download,tv,pb);
                taks.execute("http://img1.gtimg.com/0/29/2969/296943_1200x1000_0.jpg");
            }
        });
        
    }
    
}
