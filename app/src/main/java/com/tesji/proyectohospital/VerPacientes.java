package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.proyectohospital.model.ConexionSQLite;

public class VerPacientes extends AppCompatActivity {

    private ListView lisPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pacientes);

        lisPacientes = findViewById(R.id.listaPacientes);

        verPacien();

    }

    private void verPacien(){

        //Conectando con la BD
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //Dar permisos a la BD, solo de lectura-----------
        SQLiteDatabase bd = conexion.getReadableDatabase();
        //Extrayendo los datos de la tabla paciente
        String consulta = "SELECT idpaciente || ' ' || nombre || ' ' || ape1 || ' ' || ape2  || ' ' " +
                "|| telefono1 || ' ' || email1 FROM paciente";
        Cursor registroPa = bd.rawQuery(consulta, null);
        //Contabilizar el numero de registros de la consulta
        int contRegPaciente = registroPa.getCount();

        String arrayPaciente [] = new String[contRegPaciente];
        //Extraer los datos de registroPa a arrayPaciente

        //Nos posicionamos en el primer registro que extraemos
        registroPa.moveToFirst();

        int contExtraer = 0;
        while (contExtraer < contRegPaciente){
            arrayPaciente[contExtraer] = registroPa.getString(0);
            contExtraer++;
            registroPa.moveToNext();
        }

        ArrayAdapter<String> adapPaciente = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,arrayPaciente);

        lisPacientes.setAdapter(adapPaciente);

        bd.close();
        conexion.close();
    }
}