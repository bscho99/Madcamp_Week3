package com.example.madcamp_week3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arthenica.mobileffmpeg.FFmpeg;

public class MainActivity extends AppCompatActivity {

    String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    Context context;
    private DownloadService downloadService;
    private ExtractService extractService;
    private boolean downloadBound = false;
    private boolean extractBound = false;
    Button downloadButton;
    String downloadLink = "https://youtu.be/XH9NyhkXcp4";




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
            else{
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void checkPermission(Context context, String[] permissions) {
        Boolean b = true;

        for (int i=0; i <permissions.length; i++) {
            b = (ActivityCompat.checkSelfPermission(context, permissions[i]) == PackageManager.PERMISSION_GRANTED) && b;
        }

        if (!b) {
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }
    }


    private ServiceConnection downloadServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DownloadService.MyBinder binder = (DownloadService.MyBinder) iBinder;
            downloadService = binder.getService();
            downloadBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            downloadBound = false;
        }
    };


    private ServiceConnection extractServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ExtractService.MyBinder binder = (ExtractService.MyBinder) iBinder;
            extractService = binder.getService();
            extractBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            extractBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        checkPermission(context, permissions);

        downloadButton = findViewById(R.id.download_button);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DownloadService.class);
                intent.putExtra("download_link", downloadLink);
                startService(intent);

/*
                Intent intent = new Intent(getApplicationContext(), ExtractService.class);
                startService(intent);


                Intent intent = new Intent(getApplicationContext(), AppendService.class);
                startService(intent);

                 */
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(downloadBound) {
            unbindService(downloadServiceConnection);
            downloadBound = false;
        }
        stopService(new Intent(this, DownloadService.class));

        if(extractBound) {
            unbindService(extractServiceConnection);
            extractBound = false;
        }
        stopService(new Intent(this, ExtractService.class));
    }
}