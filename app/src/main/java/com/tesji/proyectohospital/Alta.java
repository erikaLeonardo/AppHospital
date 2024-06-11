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

public class Alta extends AppCompatActivity {

    Button cerrarAlta;
    Button guardarAlta;
    Button nuevoAlta;
    Button cancelarAlta;
    TextInputEditText nombreAlta;
    TextInputEditText apellido1Alta;
    TextInputEditText apellido2Alta;
    TextInputEditText telefonoAlta;
    TextInputEditText correoAlta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        guardarAlta = findViewById(R.id.guardarAl);
        nuevoAlta = findViewById(R.id.nuevoAl);
        cerrarAlta = findViewById(R.id.cerrarAl);
        nombreAlta = findViewById(R.id.nPaciente);
        apellido1Alta = findViewById(R.id.apellido1);
        apellido2Alta = findViewById(R.id.apellido2);
        telefonoAlta = findViewById(R.id.telefonoAl);
        correoAlta = findViewById(R.id.correoAl);
        cancelarAlta = findViewById(R.id.cancelarAl);
        inabilitar();

        guardarAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n_a, a1_a, a2_a, t_a, c_a;

                n_a = nombreAlta.getText().toString();
                a1_a = apellido1Alta.getText().toString();
                a2_a = apellido2Alta.getText().toString();
                t_a = telefonoAlta.getText().toString();
                c_a = correoAlta.getText().toString();

                if (nombreAlta.length() == 0){
                    nombreAlta.setError("El campo es obligatorio");
                    nombreAlta.requestFocus();
                    Toast.makeText(getApplicationContext(),"No lleno un campo",
                            Toast.LENGTH_LONG).show();
                }else if(apellido1Alta.length() == 0){
                    apellido1Alta.setError("El campo es obligatorio");
                    apellido1Alta.requestFocus();
                    Toast.makeText(getApplicationContext(),"No lleno un campo",
                            Toast.LENGTH_LONG).show();
                }else if(apellido2Alta.length() == 0){
                    apellido2Alta.setError("El campo es obligatorio");
                    apellido2Alta.requestFocus();
                    Toast.makeText(getApplicationContext(),"No lleno un campo",
                            Toast.LENGTH_LONG).show();
                }else if(telefonoAlta.length() == 0){
                    telefonoAlta.setError("El campo es oblifatorio");
                    telefonoAlta.requestFocus();
                    Toast.makeText(getApplicationContext(),"No lleno un campo",
                            Toast.LENGTH_LONG).show();
                }else if(correoAlta.length() == 0){
                    correoAlta.setError("El campo es obligatorio");
                    Toast.makeText(getApplicationContext(),"No lleno un campo",
                            Toast.LENGTH_LONG).show();
                }else{
                    registrarPaciente();
                }
            }
        });

        nuevoAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habilitar();
            }
        });

        cerrarAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cancelarAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inabilitar();
            }
        });
    }

    private void habilitar(){
        nombreAlta.setEnabled(true);
        apellido1Alta.setEnabled(true);
        apellido2Alta.setEnabled(true);
        telefonoAlta.setEnabled(true);
        correoAlta.setEnabled(true);
        nuevoAlta.setEnabled(false);
        guardarAlta.setEnabled(true);
        cancelarAlta.setEnabled(true);
        cerrarAlta.setEnabled(false);
        nombreAlta.requestFocus();
    }

    private void inabilitar(){
        nombreAlta.setEnabled(false);
        apellido1Alta.setEnabled(false);
        apellido2Alta.setEnabled(false);
        telefonoAlta.setEnabled(false);
        correoAlta.setEnabled(false);
        nuevoAlta.setEnabled(true);
        guardarAlta.setEnabled(false);
        cancelarAlta.setEnabled(false);
        cerrarAlta.setEnabled(true);

        nombreAlta.setText("");
        apellido1Alta.setText(null);
        apellido2Alta.setText("");
        telefonoAlta.setText("");
        correoAlta.setText("");
    }

    private void registrarPaciente(){

        //Conectando con la BD
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //Dar permisos a la BD
        SQLiteDatabase bd = conexion.getWritableDatabase();

        //Extraer valores del formulario
        String nombre = nombreAlta.getText().toString();
        String apellido1 = apellido1Alta.getText().toString();
        String apellido2 = apellido2Alta.getText().toString();
        String telefonoBD = telefonoAlta.getText().toString();
        String email = correoAlta.getText().toString();

        //validar los datos que no esten vacios es TAREA

        //Alamcenar el registro de un contenedor para despues
        //guardarlo en la BD
        ContentValues registro = new ContentValues();
        //key = nombre del campo de nuestra tabla
        registro.put("nombre", nombre);
        registro.put("ape1",apellido1);
        registro.put("ape2", apellido2);
        registro.put("telefono1", telefonoBD);
        registro.put("email1", email);

        //Guardar el resultado en al tabla
        long resultado = bd.insert("paciente", null, registro);
        if(resultado > 0){
            Toast.makeText(this, "Registro guardado correctamente",
                    Toast.LENGTH_SHORT).show();
            inabilitar();
        }else{
            Toast.makeText(this, "Registro no guardado, verifique los datos",
                    Toast.LENGTH_SHORT).show();
        }


        //Cerrar la conexion con la BD
        //Primero se cierra la base de datos
        bd.close();
        //Despues ña conexion
        conexion.close();

    }
}
