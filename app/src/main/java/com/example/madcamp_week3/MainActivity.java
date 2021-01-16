package com.example.madcamp_week3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arthenica.mobileffmpeg.FFmpeg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    //얘는 왜 mainactivity 에서 선언하고 가져다 쓰는걸까.... 여기 저기 fragment에서 써서 그런가..
    public static ArrayList<PlayList> playlists = new ArrayList<>();

    Button downloadButton;
    Button btn_sample1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        playlists = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadButton = findViewById(R.id.download_button);
        btn_sample1 = findViewById(R.id.btn_sample1);

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
                /*Intent intent = new Intent(getApplicationContext(), AppendService.class);
                startService(intent);*/
            }
        });
        btn_sample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raplaceFragment(new PlaylistFragment());
            }
        });

    }
    //fragment loader
    public void raplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /*Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.main_activity_fragment);*/
        fragmentTransaction.replace(R.id.main_activity_fragment, fragment);
        /*fragmentTransaction.remove(fragment1);*/
        fragmentTransaction.commit();
    }

    public String getJsonString () {
        String json = "";
        try {
            InputStream is = getAssets().open("Playlist.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }
    private void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray movieArray = jsonObject.getJSONArray("Movies");

            for(int i=0; i<movieArray.length(); i++)
            {
                JSONObject movieObject = movieArray.getJSONObject(i);

                PlayList playList = new PlayList();

                playList.setUrl(movieObject.getString("url"));
                playList.setName(movieObject.getString("title"));
                playList.setTime(movieObject.getString("time"));
                playList.setSinger(movieObject.getString("singer"));

                playlists.add(playList);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}