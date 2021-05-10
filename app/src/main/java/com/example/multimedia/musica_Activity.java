package com.example.multimedia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.multimedia.R.drawable.elcamaron;

public class musica_Activity extends AppCompatActivity {

    ImageView caratula;
    TextView titulo;
    Button play, stop, atras, adelante, pistaMenos, pistaMas;
    MediaPlayer mediaplayer;
    int reproduccion=1;

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
        setContentView(R.layout.activity_musica_);

        //asociamos variables a layaut

        caratula = (ImageView) findViewById(R.id.ViewCaratula);
        titulo = (TextView) findViewById(R.id.txtTitulo);
        play = (Button) findViewById(R.id.buttonPlay);
        stop = (Button) findViewById(R.id.buttonStop);
        pistaMenos = (Button) findViewById(R.id.buttonPistaMenos);
        pistaMas = (Button) findViewById(R.id.buttonPistaMas);
        atras = (Button) findViewById(R.id.buttonRewind);
        adelante = (Button) findViewById(R.id.buttonFordiwn);

        //cargamos la primera cancion
        cancion();
        //iniciamos ocultando el boton pause y anulando los demás
        stop.setActivated(false);
        stop.setBackgroundResource(R.drawable.stop_off);

        //implementamos los click de los botones
        //cuando pulsamos en play
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si esta reproduciendo mostramos el icono de pause
                if (mediaplayer.isPlaying())
                {
                    play.setBackgroundResource(R.drawable.play);
                    mediaplayer.pause();//si esta en play lo pausamos
                    stop.setBackgroundResource(R.drawable.stop);
                    stop.setActivated(true);

                    Toast.makeText(musica_Activity.this, "Pause", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //si estoy en pause
                    if (stop.isActivated()) {
                        play.setBackgroundResource(R.drawable.pause);
                        stop.setBackgroundResource(R.drawable.stop);
                        stop.setActivated(false);
                        mediaplayer.start();
                        Toast.makeText(musica_Activity.this, "Play", Toast.LENGTH_SHORT).show();
                    }
                    //estoy en parada, cargo cancion
                    else
                    {
                        cancion();
                        mediaplayer.start();
                        stop.setActivated(true);//activo el boton de parada
                        stop.setBackgroundResource(R.drawable.stop); //pongo activo stop
                        play.setBackgroundResource(R.drawable.pause);//pongo la opcion de pause
                        Toast.makeText(musica_Activity.this, "Play", Toast.LENGTH_SHORT).show();
                    }
                    adelante.setBackgroundResource(R.drawable.fordwin);
                    adelante.setActivated(true);
                    atras.setBackgroundResource(R.drawable.rewind);
                    atras.setActivated(true);
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaplayer.isPlaying())
                {
                    mediaplayer.stop();
                    stop.setBackgroundResource(R.drawable.stop_off);
                    stop.setActivated(false);
                    play.setBackgroundResource(R.drawable.play);
                    adelante.setBackgroundResource(R.drawable.fordwin_off);
                    adelante.setActivated(false);
                    atras.setBackgroundResource(R.drawable.rewind_off);
                    atras.setActivated(false);
                    Toast.makeText(musica_Activity.this, "Stop", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    stop.setActivated(false);
                    stop.setBackgroundResource(R.drawable.stop_off);
                }
            }
        });
        //si pulsamos para avanzar una cancion
        pistaMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaplayer.isPlaying()){
                    mediaplayer.stop();
                    play.setActivated(true);
                    play.setBackgroundResource(R.drawable.play);
                    stop.setActivated(false);
                    stop.setBackgroundResource(R.drawable.stop_off);
                }
                if (reproduccion >= 1 && reproduccion <= 4){
                    reproduccion--;
                    cancion();
                }
                Toast.makeText(musica_Activity.this, "Pista "+reproduccion, Toast.LENGTH_SHORT).show();
            }

        });
        //si pulsamos para retroceder una cancion
        pistaMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reproduccion >= 1 && reproduccion <= 4)
                {
                    if (mediaplayer.isPlaying()) {
                        mediaplayer.stop();
                        play.setActivated(true);
                        play.setBackgroundResource(R.drawable.play);
                        stop.setActivated(false);
                        stop.setBackgroundResource(R.drawable.stop_off);
                    }
                    reproduccion++;
                    cancion();
                }
                Toast.makeText(musica_Activity.this, "Pista "+reproduccion, Toast.LENGTH_SHORT).show();
            }
        });

        //avanzamos 5 segundos en la pista
        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = mediaplayer.getCurrentPosition();//vemos en que posicion estamos
                // comprobamos si el salto de 5 segundos es menor a la duracion total
                if ((posicion + 5000) <= mediaplayer.getDuration())
                {
                    mediaplayer.seekTo(posicion+5000);
                }
                else
                    mediaplayer.seekTo(mediaplayer.getDuration());
                Toast.makeText(musica_Activity.this, "+ 5 seg", Toast.LENGTH_SHORT).show();
            }
        });

        //retrocedemos 5 segundos en la pista
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = mediaplayer.getCurrentPosition(); //vemos en que posicion estamos
                //comprobamos que podemos retroceder 5 segundos o bien al principio
                if ((posicion-5000) > 0)
                {
                    mediaplayer.seekTo(posicion-5000);
                }
                else
                    mediaplayer.seekTo(0);
                Toast.makeText(musica_Activity.this, "- 5 seg", Toast.LENGTH_SHORT).show();
            }
        });
        }
    public void cancion()
    {
        switch (reproduccion){
            case 1: mediaplayer = MediaPlayer.create(this, R.raw.camaron);
                    caratula.setBackgroundResource(elcamaron);
                    titulo.setText("Camaron de la Isla");
                    pistaMenos.setActivated(false);
                    pistaMenos.setBackgroundResource(R.drawable.pista_menos_off);
                    adelante.setBackgroundResource(R.drawable.fordwin_off);
                    adelante.setActivated(false);
                    atras.setBackgroundResource(R.drawable.rewind_off);
                    atras.setActivated(false);
                    break;
            case 2: mediaplayer = MediaPlayer.create(this, R.raw.arrebato);
                    caratula.setBackgroundResource(R.drawable.elarrebato);
                    pistaMenos.setActivated(true);
                    pistaMenos.setBackgroundResource(R.drawable.pista_menos);
                    titulo.setText("El Arrebato");
                    adelante.setBackgroundResource(R.drawable.fordwin_off);
                    adelante.setActivated(false);
                    atras.setBackgroundResource(R.drawable.rewind_off);
                    atras.setActivated(false);
                    break;
            case 3: mediaplayer = MediaPlayer.create(this, R.raw.barrio);
                    caratula.setBackgroundResource(R.drawable.elbarrio);
                    titulo.setText("El Barrio");
                    pistaMas.setActivated(true);
                    pistaMas.setBackgroundResource(R.drawable.pista_mas);
                    adelante.setBackgroundResource(R.drawable.fordwin_off);
                    adelante.setActivated(false);
                    atras.setBackgroundResource(R.drawable.rewind_off);
                    atras.setActivated(false);
                    break;
            case 4: mediaplayer = MediaPlayer.create(this, R.raw.poveda);
                    caratula.setBackgroundResource(R.drawable.miguel);
                    titulo.setText("Miguel Poveda");
                    pistaMas.setActivated(false);
                    pistaMas.setBackgroundResource(R.drawable.pista_mas_off);
                    adelante.setBackgroundResource(R.drawable.fordwin_off);
                    adelante.setActivated(false);
                    atras.setBackgroundResource(R.drawable.rewind_off);
                    atras.setActivated(false);
                    break;
        }
    }

}