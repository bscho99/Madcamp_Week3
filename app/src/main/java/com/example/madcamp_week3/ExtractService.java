package com.example.madcamp_week3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

public class ExtractService extends Service {

    private IBinder binder = new ExtractService.MyBinder();
    public static boolean SERVICE_CONNECTED = false;
    String title;

    public class MyBinder extends Binder {
        public ExtractService getService() {
            return ExtractService.this;
        }
    }

    public ExtractService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "Extract service on start command");
        title = intent.getStringExtra("title");

        try {
            new AudioExtractor().genVideoUsingMuxer(Environment.getExternalStorageDirectory() + "/Download/" + title + ".mp4" ,
                    Environment.getExternalStorageDirectory() + "/Download/" + title + ".mp3",
                    -1,
                    -1,
                    true,
                    false);

            File file = new File(Environment.getExternalStorageDirectory() + "/Download/" + title + ".mp4");
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "Extract service called and created");

    }
}