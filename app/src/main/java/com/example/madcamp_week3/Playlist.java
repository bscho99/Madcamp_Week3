package com.example.madcamp_week3;

import java.util.ArrayList;

public class Playlist {
    String userid;
    ArrayList<String> playlist;

    public Playlist(String userid, ArrayList<String> playlist) {
        this.userid = userid;
        this.playlist = playlist;
    }

    public String getUserid() {
        return userid;
    }

    public ArrayList<String> getPlaylist() {
        return playlist;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPlaylist(ArrayList<String> playlist) {
        this.playlist = playlist;
    }

    public void changeOrder(int i, int j) {
        int size = playlist.size();
        if (i < size && j < size) {
            String item_i = playlist.get(i);
            String item_j = playlist.get(j);
            playlist.set(i, item_j);
            playlist.set(j, item_i);
        }
    }
}
