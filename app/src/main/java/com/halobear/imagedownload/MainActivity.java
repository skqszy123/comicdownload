package com.halobear.imagedownload;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.halobear.imagedownloadlib.DownLoadManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> imageurllist;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageurllist = new ArrayList<>();
        for(int i = 1;i<10;i++) {
            imageurllist.add("http://i.hamreus.com:8080/ps2/f/fznyjrdwd18xyxxqmm/第01回/00"+i+".jpg");
        }
        for(int i = 0;i<10;i++) {
            imageurllist.add("http://i.hamreus.com:8080/ps2/f/fznyjrdwd18xyxxqmm/第01回/01"+i+".jpg");
        }
        for(int i = 0;i<10;i++) {
            imageurllist.add("http://i.hamreus.com:8080/ps2/f/fznyjrdwd18xyxxqmm/第01回/02"+i+".jpg");
        }
        for(int i = 0;i<9;i++) {
            imageurllist.add("http://i.hamreus.com:8080/ps2/f/fznyjrdwd18xyxxqmm/第01回/03"+i+".jpg");
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute(imageurllist.get(count));
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
//            Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_LONG);
            count++;
            if(count<38){
                new Task().execute(imageurllist.get(count));
                Log.e("download ready",count+1+"");
            }else{
                Toast.makeText(MainActivity.this,"下载完成", Toast.LENGTH_LONG);
            }

        }

    }
}
