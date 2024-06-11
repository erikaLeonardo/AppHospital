package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.proyectohospital.model.ConexionSQLite;

public class VerEnfermedades extends AppCompatActivity {

    private ListView listaEnfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_enfermedades);
        listaEnfer=findViewById(R.id.listEnferme);

        verEnfermedadesLista();
    }

    private void verEnfermedadesLista(){
        //Conectando con la BD
        ConexionSQLite conexion1 = new ConexionSQLite(this,"Hospital", null, 1);
        //Dar permiso a la BD;
        SQLiteDatabase bd = conexion1.getReadableDatabase();

        //Extrayendo los datos de la tabla paciente
        String consulta1 = "SELECT idenfermedad || ' ' || nombre || ' ' || descripcion FROM enfermedad";
        Cursor registroEnfermedad = bd.rawQuery(consulta1,null);
        //Con tabilizar el numero de resgistros de la consulta
        int contRegEnfermedad = registroEnfermedad.getCount();

        String arrayEnfermedad [] = new String[contRegEnfermedad];
        //Extraer los datos de registroPciente a arrayPaciente
        //Nos posicionamos en el primer registro a extraer
        registroEnfermedad.moveToFirst();
        int contExtraer1 = 0;
        while(contExtraer1 < contRegEnfermedad){
            arrayEnfermedad[contExtraer1] = registroEnfermedad.getString(0);
            contExtraer1++;
            registroEnfermedad.moveToNext();
        }

        ArrayAdapter<String> adapEnfermedad = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,arrayEnfermedad);

        listaEnfer.setAdapter(adapEnfermedad);
        bd.close();
        conexion1.close();
    }
}