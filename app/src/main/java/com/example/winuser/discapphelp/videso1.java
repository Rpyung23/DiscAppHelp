package com.example.winuser.discapphelp;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class videso1 extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        //Quitamos barra de notificaciones

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Quitamos barra de titulo de la aplicacion

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_videso1);


        VideoView videoView = (VideoView) findViewById(R.id.videoView);



        Uri path = Uri.parse("android.resource://com.example.winuser.discapphelp/"

                + R.raw.a);

        MediaController mc = new MediaController(this);

        videoView.setMediaController(mc);

        videoView.setVideoURI(path);

        videoView.start();

    }





}
