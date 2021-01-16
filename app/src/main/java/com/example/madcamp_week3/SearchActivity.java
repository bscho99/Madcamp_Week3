package com.example.madcamp_week3;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.madcamp_week3.MainActivity.playlists;


public class SearchActivity extends AppCompatActivity {
    Button btn_search_download;
    Button btn_search_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        btn_search_download = (Button) findViewById(R.id.btn_search_download);
        btn_search_cancel = (Button) findViewById(R.id.btn_search_cancel);

        Context context = SearchActivity.this;

        String url = "http://www.youtube.com";

        //webView 만들기
        WebView webView = (WebView) findViewById(R.id.home_webview);
        //webview client 연결
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        btn_search_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면에 나와있는 영상의 url
                String str = webView.getUrl();
                Toast url = Toast.makeText(context, str, LENGTH_SHORT);
                url.show();

                playlists.add(new PlayList(str, "time", "title", "singer"));
































                /*
                 유튜브 url 정보 가지고 있는 str 가지고 동영상 다운 받고 플레이리스트에 추가
                */
                //다운로드 완료되면 다시 activity 종료

            }
        });
        btn_search_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //취소 누르면 activity 종료

                //getActivity().getFragmentManager().popBackStack();
            }
        });
    }
}
