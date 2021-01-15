package com.example.madcamp_week3;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class DownloadService extends Service {
    String downloadLink = "https://youtu.be/9N5wa98jGYo";
    String newLink;

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "Download service called and created");
        downloadMyVideo();
    }

    public void downloadMyVideo() {

        YouTubeUriExtractor youTubeUriExtractor=new YouTubeUriExtractor(DownloadService.this) {
            @Override
            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {

                if(ytFiles!=null){

                    int tag=18; // tag for a 360p .mp4 file

                    if (ytFiles.get(tag) == null) {
                        Toast.makeText(DownloadService.this, "Error occurred while trying to download the video!", Toast.LENGTH_SHORT);
                    } else {
                        Log.d("ytFile", ytFiles.get(tag).toString());
                        newLink = ytFiles.get(tag).getUrl();
                        String title = "Music";
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(newLink));
                        request.setTitle(title);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title + ".mp4");
                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        request.allowScanningByMediaScanner();
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                        downloadManager.enqueue(request);
                    }
                }
            }
        };

        youTubeUriExtractor.execute(downloadLink);

        Intent intent = new Intent(this, ExtractService.class);
        startActivity(intent);
    }


}