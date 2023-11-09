package com.example.myfirstvideoapp;

public class Videos {
    private Integer id;
    private byte[] video;

    public Videos(Integer id, byte[] video ){
        this.id=id;
        this.video= video;

    }

    public Videos(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }
}
