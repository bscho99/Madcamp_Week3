package com.example.madcamp_week3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.arthenica.mobileffmpeg.Config;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import static com.example.madcamp_week3.MainActivity.playlists;

public class PlayFragment extends Fragment {

    Button btn_add_play;
    ListView listview;
    PlayList playlist;
    PlaylistAdapter adapter;
    YouTubeThumbnailView youTubeThumbnailView;

    public PlayFragment() {
        // Required empty public constructor
    }

    public static PlayFragment newInstance() {
        PlayFragment playfragment = new PlayFragment();
        return playfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        playlist = new PlayList();

        btn_add_play = view.findViewById(R.id.btn_add_play);

        listview = (ListView) view.findViewById(R.id.lv_play);
        adapter = new PlaylistAdapter(getContext(), playlists);
        listview.setAdapter(adapter);

        //영상 썸네일 가져오기
        youTubeThumbnailView = (YouTubeThumbnailView) view.findViewById(R.id.youtubeThumbnailView);
        youTubeThumbnailView.initialize("AIzaSyDHq93rLbsAFNrNxaw3dn-69dvKa7Cr7YY", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo("FM7MFYoylVs");
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                        Toast.makeText(getActivity(), "It's a valid youtube url.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        try {
                            Toast.makeText(getActivity(), "Not a valid youtube url.", Toast.LENGTH_SHORT).show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        //영상 제목 가져오기


        btn_add_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}