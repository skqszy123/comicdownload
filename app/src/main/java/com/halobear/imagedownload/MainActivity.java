package com.halobear.imagedownload;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.halobear.imagedownloadlib.DownLoadManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute("http://images.dmzj.com/g/光速蒙面侠21/283/001.png");
            }
        });
    }

    /**
     * 异步线程下载图片
     *
     */
    public class Task extends AsyncTask<String, Integer, Bitmap> {

        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=DownLoadManager.getInstance().GetImageInputStream((String)params[0]);
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            DownLoadManager.getInstance().SavaImage(result, getExternalFilesDir(null)+"/IMAGE");
            Log.e("path", getExternalFilesDir(null)+"/IMAGE");
            Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_LONG);

        }

    }
}
