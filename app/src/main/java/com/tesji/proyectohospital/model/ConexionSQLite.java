package com.tesji.proyectohospital.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(@Nullable Context context, @Nullable String nameBD,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameBD, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE paciente(idpaciente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL, ape1 TEXT NOT NULL, ape2 TEXT NOT NULL," +
                "telefono1 TEXT, email1 TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE enfermedad(idenfermedad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT UNIQUE NOT NULL, descripcion TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE registro(idregistro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idpaciente INTEGER NOT NULL, idenfermedad INTEGER NOT NULL, fecha DATE NOT NULL," +
                "FOREIGN KEY(idpaciente) REFERENCES paciente(idpaciente)," +
                "FOREIGN KEY(idenfermedad) REFERENCES enfermedad(idenfermedad))");
        //CREATE TABLE paciente(idpaciente integer);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
