package com.example.usm.musicplayer.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.usm.musicplayer.Fragments.AllSongFragment;
import com.example.usm.musicplayer.Model.SongsList;

import java.io.IOException;
import java.util.ArrayList;

public class MyServices extends Service implements AllSongFragment.createDataParse{

    MediaPlayer mediaPlayer;
    String nameS,pathS;
    Intent intent;

    public  MyServices(){

    }


//    public MyServices(String path){
//        this.pathS=path;
//    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        try {

            pathS=intent.getExtras().getString("path");
            Toast.makeText(this,"onCreateService",Toast.LENGTH_SHORT).show();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(pathS);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }


    @Override
    public void onDataPass(String name, String path) {
        this.nameS=name;
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        this.pathS=path;
    }

    @Override
    public void fullSongList(ArrayList<SongsList> songList, int position) {

    }

    @Override
    public String queryText() {
        return null;
    }

    @Override
    public void currentSong(SongsList songsList) {

    }

    @Override
    public void getLength(int length) {

    }

}
