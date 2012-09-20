package com.bluestome.asynctask.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bluestome.asynctask.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: UrlLoadTask
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-9-19 上午11:37:21
 */
public class UrlLoadTask extends AsyncTask<String, Integer, Integer> {
    
    
    private Button download;
    private TextView tv;
    private Activity activity;
    private ProgressBar pb;
    private ByteArrayOutputStream baos = null;

    public UrlLoadTask(Activity activity,Button download,TextView tv,ProgressBar pb){
        this.activity = activity;
        this.tv = tv;
        this.download = download;
        this.pb = pb;
    }
    
    @Override
    protected Integer doInBackground(String... params) {
        int result = 400;
        URL url = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        try{
            url = new URL(params[0]);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection","keep-alive");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
            connection.connect();
            result = connection.getResponseCode();
            if(result == HttpURLConnection.HTTP_OK){
                baos = new ByteArrayOutputStream();
                is = connection.getInputStream();
                int total = connection.getContentLength();
                int c;
                byte[] tmp = new byte[2048];
                pb.setMax(total);
                while((c = is.read(tmp)) != -1){
                    baos.write(tmp, 0, c);
                    pb.setProgress(baos.size());
                    publishProgress(baos.size(),total);
                }
                baos.flush();
                baos.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != connection)
                connection.disconnect();
        }
        return result;
    }
    
    @Override
    protected void onProgressUpdate(Integer... progress) {
        //转成百分比
        java.text.NumberFormat percentFormat =java.text.NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        Float pres = (float)progress[0]/(float)progress[1];
//        activity.setTitle("正在执行..."+percentFormat.format(pres));
        tv.setText("正在执行..."+percentFormat.format(pres));
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch(result){
            case 200:
                tv.setText(tv.getText()+".....成功!");
                download.setEnabled(true);
                pb.setVisibility(View.GONE);
                Toast.makeText(activity, "下载文件成功", Toast.LENGTH_SHORT).show();
                if(null != baos && baos.size() > 0){
                    byte[] body = baos.toByteArray();
//                    Bitmap bitMap = BitmapFactory.decodeByteArray(body, 0, body.length);
//                    ImageView imageView = ((ImageView)activity.findViewById(R.id.image_view));
//                    imageView.setVisibility(View.VISIBLE);
//                    imageView.setImageBitmap(bitMap);
                    baos.reset();
                    baos = null;
                }
                break;
            default:
                tv.setText(tv.getText()+".....失败!");
                download.setEnabled(true);
                pb.setVisibility(View.GONE);
                Toast.makeText(activity, "下载文件失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * @return the download
     */
    public Button getDownload() {
        return download;
    }

    /**
     * @param download the download to set
     */
    public void setDownload(Button download) {
        this.download = download;
    }

    /**
     * @return the tv
     */
    public TextView getTv() {
        return tv;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(TextView tv) {
        this.tv = tv;
    }

    /**
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return the pb
     */
    public ProgressBar getPb() {
        return pb;
    }

    /**
     * @param pb the pb to set
     */
    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }



    
}
