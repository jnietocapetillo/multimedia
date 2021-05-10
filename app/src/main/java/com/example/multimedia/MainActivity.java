package com.example.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView lienzo, musica, video, camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lienzo = (ImageView) findViewById(R.id.imgLienzo);
        musica = (ImageView) findViewById(R.id.imgMusica);
        video = (ImageView) findViewById(R.id.imgVideo);
        camara = (ImageView) findViewById(R.id.imgCamara);

        //si pulsamos sobre lienzo nos vamos a la actividad de lienzo
        lienzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), canvas_Activity.class);
                startActivity(i);
            }
        });
        //si pulsamos sobre musica nos vamos a la actividad de musica
        musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), musica_Activity.class);
                startActivity(i);
            }
        });
        //si pulsamos en video nos vamos a la actividad de video
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), video_Activity.class);
                startActivity(i);
            }
        });
        //si pulsamos sobre la camara, nos vamos a la actividad de camara
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), camara_Activity.class);
                startActivity(i);
            }
        });
    }
}