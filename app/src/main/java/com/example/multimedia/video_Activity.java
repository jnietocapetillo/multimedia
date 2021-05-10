package com.example.multimedia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class video_Activity extends AppCompatActivity {

    VideoView reproductor;
    TextView titulo;
    ImageView video1, video2, video3;
    int reproduccion=1;
    Uri uri1, uri2, uri3;

    //ponemos un menu en la barra superior de la actividad con la opcion de personaliar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //recogemos el click de la opcion del menu en la barra de aplicaciones
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        startActivity(new Intent(this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_);

        reproductor = (VideoView) findViewById(R.id.videoView);
        titulo = (TextView) findViewById(R.id.txtTitulo);
        video1 = (ImageView) findViewById(R.id.imgVideo1);
        video2 = (ImageView) findViewById(R.id.imgVideo2);
        video3 = (ImageView) findViewById(R.id.imgVideo3);
        uri1 = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.doma_clasica);
        uri2 = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.miguel_maria);
        uri3 = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.torreluna);

        //creamos un controlador para nuestro reproductor
        reproductor.setMediaController(new MediaController(this));

        //creamos los click en las imagenes que nos llevaran a los distintos videos

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproduccion = 1;
                video1.setBackgroundResource(R.drawable.ok);
                video2.setBackgroundResource(R.drawable.video2);
                video3.setBackgroundResource(R.drawable.video3);
                video();
            }

        });

        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproduccion = 2;
                video2.setBackgroundResource(R.drawable.ok);
                video1.setBackgroundResource(R.drawable.video1);
                video3.setBackgroundResource(R.drawable.video3);
                video();
            }
        });

        video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproduccion = 3;
                video3.setBackgroundResource(R.drawable.ok);
                video1.setBackgroundResource(R.drawable.video1);
                video2.setBackgroundResource(R.drawable.video2);
                video();
            }
        });

    }

    public void video()
    {
        switch (reproduccion)
        {
            case 1: reproductor.setVideoURI(uri1);
                    titulo.setText("El arte de la Doma clásica");break;
            case 2: reproductor.setVideoURI(uri2);
                    titulo.setText("Miguel Poveda y Maria Jimenez");break;
            case 3: reproductor.setVideoURI(uri3);
                    titulo.setText("Yeguada Torreluna");break;
        }
    }
}