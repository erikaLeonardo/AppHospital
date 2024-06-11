package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tesji.proyectohospital.model.ConexionSQLite;

public class RegistrarRegistros extends AppCompatActivity {

    Button guardar1;
    Button nuevo1;
    Button cerrar;
    Button buscarPA;
    Spinner opcionEnfermedad;
    TextInputEditText fecha1;
    TextInputEditText nomB;
    TextInputEditText apeLL;
    TextInputEditText idP;
    TextInputEditText idE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_registros);

        guardar1 = findViewById(R.id.guardarRR);
        nuevo1 = findViewById(R.id.nuevoRR);
        cerrar = findViewById(R.id.cerrarRR);
        buscarPA = findViewById(R.id.buscarReg);
        fecha1 = findViewById(R.id.fechaR);
        nomB = findViewById(R.id.nOM);
        apeLL = findViewById(R.id.ape2llo);
        opcionEnfermedad = findViewById(R.id.opcionE);
        //datosP = findViewById(R.id.datosPaciente);
        idP = findViewById(R.id.idPaciente);
        //idE = findViewById(R.id.id)

        nomB.setEnabled(false);
        apeLL.setEnabled(false);

        verEnfermedad();
        //consultarEnfermedad();

        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //registrarRegistro();
                registrarcita();
            }
        });

        nuevo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        buscarPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buscarPaciente();
            }
        });
    }

    private void verEnfermedad() {

        //conctando a una base de datos
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion.getWritableDatabase();
        //extrayendo los datos de la tabla paciente
        String consulta = "SELECT nombre FROM enfermedad";
        Cursor registroPaciente = bd.rawQuery(consulta, null);
        //contabilizar el numero de registros de la consulta
        int contadorPacientes = registroPaciente.getCount();

        //se posiciona en le primer registro a extraer
        registroPaciente.moveToFirst();
        String arrayPacientes[] = new String[contadorPacientes];
        //extraer los datos del registro paciente a array paciente

        int contExtraer = 0;
        while (contExtraer < contadorPacientes) {

            arrayPacientes[contExtraer] = registroPaciente.getString(0);
            contExtraer++;
            registroPaciente.moveToNext();
        }

        ArrayAdapter<String> adapterPacientes = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayPacientes);

        opcionEnfermedad.setAdapter(adapterPacientes);
    }

    private void buscarPaciente() {
        // conecter la DB
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String codigo = idP.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor cursor = bd.rawQuery("SELECT nombre, ape1 FROM paciente WHERE idpaciente='" + codigo + "'", null);
            if (cursor.moveToFirst()) {
                nomB.setText(cursor.getString(0));
                apeLL.setText(cursor.getString(1));
                bd.close();
            } else {
                Toast.makeText(this, "No existe ningun registro", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Introduzca el ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarcita() {
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null,1);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        ContentValues registro = new ContentValues();

        if (idP.length() == 0){
            idP.setError("El campo es obligatorio");
            idP.requestFocus();
            Toast.makeText(getApplicationContext(),"No lleno un campo",
                    Toast.LENGTH_LONG).show();
        }else if(fecha1.length() == 0){
            fecha1.setError("El campo es obligatorio");
            fecha1.requestFocus();
            Toast.makeText(getApplicationContext(),"No lleno un campo",
                    Toast.LENGTH_LONG).show();
        }else{
            String idpaciente = idP.getText().toString();
            String idEnfermedad = opcionEnfermedad.getSelectedItem().toString();
            String fech = fecha1.getText().toString();

            registro.put("idpaciente", idpaciente);
            registro.put("idenfermedad", idEnfermedad);
            registro.put("fecha",fech);
        }

        long resultado = bd.insert("registro", null, registro);
        Toast.makeText(this,"Registro Guardado Correctamente", Toast.LENGTH_LONG).show();
        bd.close();
        conexion.close();
    }
}