package com.example.multimedia;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class camara_Activity extends AppCompatActivity {

    private ImageView galeria;
    private Button acceso;
    String rutaImagen;

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
        setContentView(R.layout.activity_camara_);

        //instanciamos nuestros componentes
        galeria = (ImageView) findViewById(R.id.viewImagenes);
        acceso = (Button) findViewById(R.id.abrirCamara);

        //permisos para android 6 o superior
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
        acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

    }
    private void abrirCamara()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;
        //comprobamos que se ha guardado temporalmente ña imagen
        try {
            imagenArchivo = crearImagen();
        }catch (IOException ex){
            Log.e("Error",ex.toString());
        }

        if (imagenArchivo !=null)
        {
            Uri fotoUri = FileProvider.getUriForFile(this, "com.example.multimedia.fileprovider",imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
            startActivityForResult(intent,1);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            //Bundle extras = data.getExtras();
            Bitmap imagenBitmap = BitmapFactory.decodeFile(rutaImagen);//mostramos la imagen guardada
            galeria.setImageBitmap(imagenBitmap);
        }
    }

    private File crearImagen() throws IOException {
        String nombre = "imagen_"; //nombre de la imagen que cambiara con un metodo MD5
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //directorio donde vamos a alamcenar la imagen
        File imgTemp = File.createTempFile(nombre,".jpg",directorio);

        rutaImagen = imgTemp.getAbsolutePath();
        return imgTemp;
    }
}