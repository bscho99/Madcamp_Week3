package com.example.madcamp_week3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<PlayList> playlist;

    public PlaylistAdapter (Context context, ArrayList<PlayList> playlist) {
        mContext = context;
        playlist = playlist;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return playlist.size();
    }

    @Override
    public PlayList getItem(int position) {
        return playlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View listview = mLayoutInflater.inflate(R.layout.listview_custom, null);

        TextView lv_title = (TextView) listview.findViewById(R.id.lv_title);
        TextView lv_time = (TextView) listview.findViewById(R.id.lv_time);
        TextView lv_singer = (TextView) listview.findViewById(R.id.lv_singer);

        lv_title.setText(playlist.get(position).getName());
        lv_time.setText(playlist.get(position).getTime());
        lv_singer.setText(playlist.get(position).getSinger());

        return listview;
    }
}
