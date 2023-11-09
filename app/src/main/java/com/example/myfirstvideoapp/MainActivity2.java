package com.example.myfirstvideoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {


    SQLiteConexion conexion;
    ListView listView;

    ArrayList<Videos> listVideos;
    ArrayList<String> arregloVideos;
    int selectedPosition = ListView.INVALID_POSITION;

    private byte[] selectedVideo;

    private ArrayList<byte[]> arregloVideosVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        conexion = new SQLiteConexion(this, Transacciones.nameDB, null, 1);
        arregloVideosVideos = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        arregloVideos = new ArrayList<String>();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, arregloVideos);
        listView.setAdapter(adp);

        getVideos();


    }

    private void getVideos() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Videos video;
        listVideos = new ArrayList<Videos>();

        Cursor cursor = db.rawQuery(Transacciones.SelectTableVideos, null);
        while (cursor.moveToNext()) {
            video = new Videos();
            video.setId(cursor.getInt(0));
            video.setVideo(cursor.getBlob(1));


            listVideos.add(video);
        }

        cursor.close();

        FillList();
    }

    private void FillList() {
        for (int i = 0; i < listVideos.size(); i++) {
            arregloVideos.add(listVideos.get(i).getId() + " - Video " + (i + 1)); // Ejemplo: "2 - Video 1"
            arregloVideosVideos.add(listVideos.get(i).getVideo());
        }
    }


}



