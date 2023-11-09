package com.example.myfirstvideoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnCapture, btnSave;
    VideoView videoView;
    Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapture = (Button) findViewById(R.id.btnCapture);
        btnSave = (Button) findViewById(R.id.btnSave);
        videoView = findViewById(R.id.videoView);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permisos();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarVideo();
            }
        });


    }

    private void guardarVideo() {
       try {
           SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.nameDB, null, 1);
           SQLiteDatabase db = conexion.getWritableDatabase();
           ContentValues valores = new ContentValues();
           valores.put(Transacciones.video, String.valueOf(videoUri));

           Long Result = db.insert(Transacciones.Tabla1, Transacciones.id, valores);

           Toast.makeText(this, getString(R.string.Respuesta), Toast.LENGTH_SHORT).show();
       } catch (Exception exception) {
           Toast.makeText(this, getString(R.string.ErrorResp), Toast.LENGTH_SHORT).show();
       }


    }




    private void permisos() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 101);

        } else {
            capturarVideo();
        }
    }

    private void capturarVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 102);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102 && resultCode == RESULT_OK) {
            videoUri = data.getData();

            // Configura el VideoView para reproducir el video capturado
            videoView.setVideoURI(videoUri);

            // Inicia la reproducción del video
            videoView.start();
        }
    }

}