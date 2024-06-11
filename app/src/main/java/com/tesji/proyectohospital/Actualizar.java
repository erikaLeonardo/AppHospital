package com.tesji.proyectohospital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tesji.proyectohospital.model.ConexionSQLite;

public class Actualizar extends AppCompatActivity {

    Button guardarA;
    Button buscarA;
    Button eliminar;
    Button cerrarA;

    TextInputEditText id_PaciA;
    TextInputEditText nombreA;
    TextInputEditText ape1A;
    TextInputEditText ape2A;
    TextInputEditText telefA;
    TextInputEditText email3A;

    private int filas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        guardarA = findViewById(R.id.guardarA1);
        buscarA = findViewById(R.id.buscarA1);
        eliminar = findViewById(R.id.eliminarA1);
        cerrarA = findViewById(R.id.cerrarA1);
        id_PaciA = findViewById(R.id.idP2);
        nombreA = findViewById(R.id.nombreP2);
        ape1A = findViewById(R.id.ape1_2);
        ape2A = findViewById(R.id.ape2_2);
        telefA = findViewById(R.id.telefonoAc);
        email3A = findViewById(R.id.correoAc);

        guardarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarPaciente();
            }
        });

        buscarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarPaciente();

            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Actualizar.this);
                builder.setMessage("Desea ELIMINAR este paciente")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                eliminarPaciente();
                            }
                        }).setNegativeButton("CANCELAR", null);
                AlertDialog alerta = builder.create();
                alerta.show();
            }
        });

        cerrarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void buscarPaciente() {
        // conecter la DB
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String codigo = id_PaciA.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor cursor = bd.rawQuery("SELECT nombre, ape1, ape2, telefono1, email1 FROM paciente WHERE idpaciente='" + codigo + "'", null);
            if (cursor.moveToFirst()) {
                nombreA.setText(cursor.getString(0));
                ape1A.setText(cursor.getString(1));
                ape2A.setText(cursor.getString(2));
                telefA.setText(cursor.getString(3));
                email3A.setText(cursor.getString(4));
                bd.close();
            } else {
                Toast.makeText(this, "No existe ningun registro", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese el id del paciente", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarPaciente() {
        // conecter la DB
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String idpaciente = id_PaciA.getText().toString();

        if (!idpaciente.isEmpty()) {
            filas = bd.delete("paciente", "idpaciente=" + idpaciente, null);
            bd.close();

            id_PaciA.setText("");
            nombreA.setText("");
            ape1A.setText("");
            ape2A.setText("");
            telefA.setText("");
            email3A.setText("");
            if (filas > 0) {
                Toast.makeText(this, "Registro eliminado correctamente",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese el id del paciente",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarPaciente() {
        // conecter la DB
        ConexionSQLite conexion = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion.getWritableDatabase();

        //extraer valores del formulario
        String nombrepac = nombreA.getText().toString();
        String apellidoP = ape1A.getText().toString();
        String apellidoM = ape2A.getText().toString();
        String telefon = telefA.getText().toString();
        String eMail = email3A.getText().toString();
        String idpaciente = id_PaciA.getText().toString();


        if (!idpaciente.isEmpty() && !nombrepac.isEmpty() && !apellidoP.isEmpty() && !apellidoM.isEmpty() && !telefon.isEmpty() && !eMail.isEmpty()) {
            //almacenar los registros en un contenedor para despues mandarlo a la base de datos
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombrepac);
            registro.put("ape1", apellidoP);
            registro.put("ape2", apellidoM);
            registro.put("telefono1", telefon);
            registro.put("email1", eMail);
            Cursor cursor = bd.rawQuery("SELECT nombre, ape1, ape2, telefono1, email1 " +
                    "FROM paciente WHERE idpaciente='" + idpaciente + "'", null);
            if (cursor.moveToFirst()) {
                filas = bd.update("paciente", registro, "idpaciente=" + idpaciente, null);
                if(filas>0){
                    Toast.makeText(this, "Registro actuzalizado correctamente",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "No existe ningun registro",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ingrese el id del paciente",
                        Toast.LENGTH_SHORT).show();
            }

            //cerrar la conexion
            bd.close();
            conexion.close();
        }
    }
}