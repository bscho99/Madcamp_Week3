package com.example.madcamp_week3;

public class PlayList {

    private String url;
    private String time;
    private String name;
    private String singer;



    public String getUrl() { return url; }
    public String getTime(){
        return  time;
    }
    public String getName(){
        return name;
    }
    public String getSinger() { return singer; }

    public void setUrl(String url) { this.url = url; }
    public void setTime(String time) {
        this.time = time;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSinger(String singer) {this.singer = singer;}
}
