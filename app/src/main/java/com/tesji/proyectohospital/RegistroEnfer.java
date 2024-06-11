package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tesji.proyectohospital.model.ConexionSQLite;

public class RegistroEnfer extends AppCompatActivity {

    Button guardarE;
    Button nuevoE;
    Button cancelarE;
    Button cerrarE;
    TextInputEditText nombreE;
    TextInputEditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_enfer);

        guardarE=findViewById(R.id.guardarEE);
        nuevoE=findViewById(R.id.nuevoEE);
        cancelarE=findViewById(R.id.cancelarEE);
        cerrarE=findViewById(R.id.cerrarEE);
        nombreE=findViewById(R.id.nombreEnfe);
        descripcion=findViewById(R.id.descripcion);
        inhabilitar();

        nuevoE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habilitar();
            }
        });

        cancelarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inhabilitar();
            }
        });

        guardarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarEnfermedad();
            }
        });

        cerrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void habilitar(){
        nombreE.setEnabled(true);
        descripcion.setEnabled(true);
        guardarE.setEnabled(true);
        cancelarE.setEnabled(true);
        nuevoE.setEnabled(false);
        nombreE.requestFocus();
    }

    private void inhabilitar(){
        nombreE.setEnabled(false);
        descripcion.setEnabled(false);
        guardarE.setEnabled(false);
        cancelarE.setEnabled(false);
        nuevoE.setEnabled(true);
    }

    private void registrarEnfermedad() {
        //Conectando con la BD
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //Dar permiso a la BD;
        SQLiteDatabase bd = conexion1.getWritableDatabase();

        //Extraer valores del formulario
        //Almacenar el registro en un contenedor para despues guardarlo en la BD
        ContentValues registro1 = new ContentValues();

        if (nombreE.length() == 0) {
            nombreE.setError("El campo es obligatorio");
            nombreE.requestFocus();
            Toast.makeText(getApplicationContext(),"No lleno un campo",
                    Toast.LENGTH_LONG).show();
        } else if (descripcion.length() == 0) {
            descripcion.setError("El campo es obligatorio");
            descripcion.requestFocus();
            Toast.makeText(getApplicationContext(),"No lleno un campo",
                    Toast.LENGTH_LONG).show();
        } else {
            String nombreEnfermedad = nombreE.getText().toString();
            String descripcionEnfermedad = descripcion.getText().toString();

            //key = nombre de nuestra tabla donde se almacenara ese valor
            registro1.put("nombre", nombreEnfermedad);
            registro1.put("descripcion", descripcionEnfermedad);
        }


        //Guardando el registro en la tabla
        long resultados1 = bd.insert("enfermedad", null, registro1);

        if (resultados1 > 0) {
            Toast.makeText(this, "Registro guardado correctamente",
                    Toast.LENGTH_SHORT).show();
            inhabilitar();
        } else {
            Toast.makeText(this, "Registro no guardado, verifique los datos",
                    Toast.LENGTH_SHORT).show();
        }
        //Cerrar la conexion de la BD
        //Primera se borra la base de datos
        bd.close();
        //Despues la conexion
        conexion1.close();
    }
}