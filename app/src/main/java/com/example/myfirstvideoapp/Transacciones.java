package com.example.myfirstvideoapp;

public class Transacciones
{
    // Nombre de la base de datos
    public static final String nameDB = "videosdb";

    //Tablas de la base de datos
    public static final String Tabla1  = "videos";


    // Campos de la tabla
    public static final String id = "id";

    public static final String video = "video";

    public static final String DeleteContact = "DELETE FROM " + Transacciones.Tabla1 + " WHERE " + Transacciones.id + " = ?";


    // Consultas de Base de datos
    //ddl


    public static final String CreateTableVideos = "CREATE TABLE videos " +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT, video BLOB)";


    public static final String DropTableVideos  = "DROP TABLE IF EXISTS videos";

    public static final String SelectTableVideos = "SELECT * FROM " + Transacciones.Tabla1;




}