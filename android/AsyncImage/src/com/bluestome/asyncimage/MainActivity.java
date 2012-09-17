
package com.bluestome.asyncimage;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadImage4("http://www.baidu.com/img/baidu_logo.gif", R.id.imageView1);
        loadImage4("http://www.chinatelecom.com.cn/images/logo_new.gif",
                R.id.imageView2);
        loadImage4("http://cache.soso.com/30d/img/web/logo.gif",
                R.id.imageView3);
        loadImage4("http://csdnimg.cn/www/images/csdnindex_logo.gif",
                R.id.imageView4);
        loadImage4("http://images.cnblogs.com/logo_small.gif",
                R.id.imageView5);
    }

    private AsyncImageLoader3 asyncImageLoader3 = new AsyncImageLoader3();

    // 引入线程池，并引入内存缓存功能,并对外部调用封装了接口，简化调用过程
    private void loadImage4(final String url, final int id) {
        // 如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
        Drawable cacheImage = asyncImageLoader3.loadDrawable(url,
                new AsyncImageLoader3.ImageCallback() {
                    // 请参见实现：如果第一次加载url时下面方法会执行
                    public void imageLoaded(Drawable imageDrawable) {
                        ((ImageView) findViewById(id))
                                .setImageDrawable(imageDrawable);
                    }
                });
        if (cacheImage != null) {
            ((ImageView) findViewById(id)).setImageDrawable(cacheImage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
