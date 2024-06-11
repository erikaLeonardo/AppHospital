package com.tesji.proyectohospital;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tesji.proyectohospital.model.ConexionSQLite;

import java.util.ArrayList;

public class Pacientes_recy extends AppCompatActivity {

    ArrayList<Pacientes> listaPacien;
    RecyclerView recyclerViewPa;
    //ConexionSQLite conn;

    //ArrayList<PacienteVeo> listaPa;
    //public AdaptadorPaciente(ArrayList<PacienteVeo> listaPa) {
      //  this.listaPa = listaPa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacientes_recy);

        //conn = new ConexionSQLite(getApplicationContext(), "Hospital", null, 1);

        listaPacien = new ArrayList<>();

        recyclerViewPa = (RecyclerView) findViewById(R.id.recyclerid);
        recyclerViewPa.setLayoutManager(new LinearLayoutManager(this));
        //AdaptadorPaciente adapter = new AdaptadorPaciente(listaPacien);
        //recyclerViewPa.setAdapter(adapter);

        consultarPa();
    }

    private void consultarPa(){

        //SQLiteDatabase bd = conn.getReadableDatabase();

        //Alta paci = null;
        //String consulta = "SELECT * FROM paciente";
        //Cursor registroPa = bd.rawQuery(consulta, null);

        //while(registroPa.moveToNext()){
            //paci.nombreAlta(registroPa.getString(1));
        //}
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //Dar permisos a la BD, solo de lectura-----------
        SQLiteDatabase bd = conexion.getReadableDatabase();
        //Extrayendo los datos de la tabla paciente
        String consulta = "SELECT * FROM paciente";
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



        //recyclerViewPa.setAdapter(adapPaciente);

        bd.close();
        conexion.close();
    }
}
