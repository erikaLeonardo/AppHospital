package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.proyectohospital.model.ConexionSQLite;

public class VerRegistros extends AppCompatActivity {

    ListView listaRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);

        listaRegistros = findViewById(R.id.listRegis);

        verRegistrosLista();
    }

    private void verRegistrosLista(){

        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);

        SQLiteDatabase bd = conexion.getWritableDatabase();

        String consulta = "SELECT idregistro || ' ' || paciente.nombre || ' ' || paciente.ape1 || ' ' " +
                "|| paciente.ape2 || ' ' || idenfermedad || ' ' || fecha " +
                "FROM registro " +
                "INNER JOIN paciente ON paciente.idpaciente=registro.idpaciente";
        Cursor registroPaciente = bd.rawQuery(consulta, null);
        //contabilizar el numero de registros de la consulta
        int contadorPacientes = registroPaciente.getCount();

        //se posiciona en le primer registro a extraer
        registroPaciente.moveToFirst();
        String arrayPacientes [] = new String[contadorPacientes];
        //extraer los datos del registro paciente a array paciente

        int contExtraer=0;
        while (contExtraer < contadorPacientes){

            arrayPacientes[contExtraer] = registroPaciente.getString(0);
            contExtraer++;
            registroPaciente.moveToNext();
        }

        ArrayAdapter<String> adapterPacientes = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arrayPacientes);

        listaRegistros.setAdapter(adapterPacientes);

        bd.close();
        conexion.close();
    }
}