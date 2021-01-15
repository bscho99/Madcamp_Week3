package com.example.madcamp_week3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arthenica.mobileffmpeg.FFmpeg;

public class MainActivity extends AppCompatActivity {

    Button downloadButton;
    String downloadLink = "https://youtu.be/9N5wa98jGYo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadButton = findViewById(R.id.download_button);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(getApplicationContext(), DownloadService.class);
                // intent.putExtra("download_link", downloadLink);
                startService(intent);


                Intent intent = new Intent(getApplicationContext(), ExtractService.class);
                startService(intent);

                 */
                Intent intent = new Intent(getApplicationContext(), AppendService.class);
                startService(intent);
            }
        });
    }

}