package com.android.video;

import android.media.MediaPlayer;
import android.view.Surface;

import java.io.IOException;

/**
 * Created by peiboning on 2017/8/2.
 */

public class MediaPlayerManager {
    private static MediaPlayerManager sInstance = null;

    private MediaPlayer mp;
    private int defWidth;
    private int defHeight;
    private int currentTime = 0;
    private int videoLength = 0;

    public static MediaPlayerManager getInstance() {
        if (null == sInstance) {
            synchronized (MediaPlayerManager.class) {
                if (null == sInstance) {
                    sInstance = new MediaPlayerManager();
                }
            }
        }
        return sInstance;
    }

    public void setDef(int width, int height){
        defHeight = width;
        defHeight = height;
    }

    public int getDefWidth(){
        return defWidth;
    }

    public int getDefHeight(){
        return defHeight;
    }

    private MediaPlayerManager() {
        mp = new MediaPlayer();
    }

    public void release(){
        mp.release();
    }

    public void reset(){
        mp.reset();
    }

    public boolean setDataSource(String uri){
        try {
            mp.setDataSource(uri);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPlaying(){
        return mp.isPlaying();
    }

    public void seekTo(int pos){
        mp.seekTo(pos);
    }

    public void prepareAsync(){
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
//                mp.start();
            }
        });
        mp.prepareAsync();
    }

    public void start(){
        mp.start();
    }
    public void stop(){
        mp.stop();
    }
    public void setSurface(Surface surface){
        mp.setSurface(surface);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener){
        mp.setOnPreparedListener(listener);
    }
    public int getDuration(){
        return mp.getDuration();
    }
    public int getCurrentPosition(){
        return mp.getCurrentPosition();
    }
    public void setDuration(int lenght){
        videoLength = lenght;
    }
    public int getDuration1(){
        return videoLength;
    }
    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener listener){
        mp.setOnBufferingUpdateListener(listener);
    }

}