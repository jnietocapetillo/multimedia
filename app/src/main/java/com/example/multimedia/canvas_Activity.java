package com.example.multimedia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class canvas_Activity extends AppCompatActivity implements View.OnClickListener{

    Button azul, rojo, verde, mostaza, agua, negro, amarillo;
    Button pincel1, pincel2, pincel3, pincel4, pincel5;
    Lienzo_clase lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_);

        azul = (Button) findViewById(R.id.buttonAzul);
        rojo = (Button) findViewById(R.id.buttonRojo);
        verde = (Button) findViewById(R.id.buttonVerde);
        mostaza = (Button) findViewById(R.id.buttonMostaza);
        agua = (Button) findViewById(R.id.buttonAgua);
        negro = (Button) findViewById(R.id.buttonNegro);
        amarillo = (Button) findViewById(R.id.buttonAmarillo);
        pincel1 = (Button)findViewById(R.id.btGrosor1);
        pincel2 = (Button)findViewById(R.id.btGrosor2);
        pincel3 =(Button) findViewById(R.id.btGrosor3);
        pincel4 = (Button)findViewById(R.id.btGrosor4);
        pincel5 = (Button)findViewById(R.id.btGrosor5);
        lienzo = (Lienzo_clase) findViewById(R.id.lienzo);

        azul.setOnClickListener(this);
        rojo.setOnClickListener(this);
        verde.setOnClickListener(this);
        mostaza.setOnClickListener(this);
        agua.setOnClickListener(this);
        negro.setOnClickListener(this);
        amarillo.setOnClickListener(this);
        pincel1.setOnClickListener(this);
        pincel2.setOnClickListener(this);
        pincel3.setOnClickListener(this);
        pincel4.setOnClickListener(this);
        pincel5.setOnClickListener(this);

        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btGrosor1:
                lienzo.setStroke(10);
                break;
            case R.id.btGrosor2:
                lienzo.setStroke(20);
                break;
            case R.id.btGrosor3:
                lienzo.setStroke(30);
                break;
            case R.id.btGrosor4:
                lienzo.setStroke(40);
                break;
            case R.id.btGrosor5:
                lienzo.setStroke(50);
                break;
            case R.id.buttonAzul:
                lienzo.setColor("#1b11a9");
                break;
            case R.id.buttonRojo:
                lienzo.setColor("#c70c22");
                break;
            case R.id.buttonVerde:
                lienzo.setColor("#33c70c");
                break;
            case R.id.buttonAmarillo:
                lienzo.setColor("#fef964");
                break;
            case R.id.buttonAgua:
                lienzo.setColor("#25e3c6");
                break;
            case R.id.buttonNegro:
                lienzo.setColor("#0f0f0f");
                break;
            case R.id.buttonMostaza:
                lienzo.setColor("#e3bd25");
                break;
        }
    }


}