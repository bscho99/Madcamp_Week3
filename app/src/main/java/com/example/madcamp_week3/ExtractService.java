package com.example.madcamp_week3;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;


public class ExtractService extends Service {
    public ExtractService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "Extract service called and created");

        try {
            new AudioExtractor().genVideoUsingMuxer(Environment.getExternalStorageDirectory() + "/Download/Music-2.mp4",
                    Environment.getExternalStorageDirectory() + "/Download/target-1.mp3",
                    -1,
                    -1,
                    true,
                    false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}