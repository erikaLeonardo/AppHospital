package com.tesji.proyectohospital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tesji.proyectohospital.model.ConexionSQLite;

public class actualizarEnfe extends AppCompatActivity {

    Button buscarE;
    Button eliminarE;
    Button actualizarE;
    TextInputEditText idEnferE;
    TextInputEditText nombreE;
    TextInputEditText descripcion;

    private int listaEnfer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_enfe);

        buscarE = findViewById(R.id.buscarE);
        eliminarE = findViewById(R.id.eliminarE);
        actualizarE = findViewById(R.id.actuE);
        idEnferE = findViewById(R.id.idEnferAc);
        nombreE = findViewById(R.id.nombreEnfeA);
        descripcion = findViewById(R.id.descripcionA);

        nombreE.setEnabled(false);
        descripcion.setEnabled(false);

        buscarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buscarEnfermedad();
            }
        });

        eliminarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(actualizarEnfe.this);
                builder.setMessage("Esta seguro de eliminar la enfermedad")
                        .setPositiveButton("SI",new DialogInterface.OnClickListener(){
                            @Override
                            public  void onClick(DialogInterface dialog ,int which){
                                eliminarEnfermedad();
                            }
                        })
                        .setNegativeButton("CANCELAR",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        actualizarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(actualizarEnfe.this);
                builder.setMessage("Esta seguro de actualizar la Enfermedad")
                        .setPositiveButton("SI",new DialogInterface.OnClickListener(){
                            @Override
                            public  void onClick(DialogInterface dialog ,int which){
                                actualizarEnfermedad();
                            }
                        })
                        .setNegativeButton("CANCELAR",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void buscarEnfermedad() {
        // conecter la DB
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion1.getReadableDatabase();
        String codigo = idEnferE.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor cursor = bd.rawQuery("SELECT nombre, descripcion FROM enfermedad WHERE idenfermedad='" + codigo + "'", null);
            if (cursor.moveToFirst()) {
                nombreE.setText(cursor.getString(0));
                descripcion.setText(cursor.getString(1));

                bd.close();
            } else {
                Toast.makeText(this, "No existe ningun registro",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ingrese el id de la enfermedad",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarEnfermedad() {
        // conecter la DB
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion1.getReadableDatabase();
        String idenfermedad = idEnferE.getText().toString();

        if (!idenfermedad.isEmpty()) {
            listaEnfer = bd.delete("enfermedad", "idenfermedad=" + idenfermedad, null);
            bd.close();
            idEnferE.setText("");
            nombreE.setText("");
            descripcion.setText("");

            if (listaEnfer > 0) {
                Toast.makeText(this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ingrese el id de la enfermedad", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarEnfermedad() {
        // conecter la DB
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion1.getWritableDatabase();

        //extraer valores del formulario
        String idenfermedad = idEnferE.getText().toString();
        String nombreEnf = nombreE.getText().toString();
        String descripcionE = descripcion.getText().toString();


        if (!idenfermedad.isEmpty() && !nombreEnf.isEmpty() && !descripcionE.isEmpty() ) {
            //almacenar los registros en un contenedor para despues mandarlo a la base de datos
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombreEnf);
            registro.put("descripcion", descripcionE);

            Cursor cursor = bd.rawQuery("SELECT nombre, descripcion FROM enfermedad " +
                    "WHERE idenfermedad='" + idenfermedad + "'", null);
            if (cursor.moveToFirst()) {
                listaEnfer = bd.update("enfermedad", registro, "idenfermedad=" + idenfermedad, null);
                if(listaEnfer>0){
                    Toast.makeText(this, "Registro actuzalixado correctamente",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "No existe ningun registro",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ingrese el id de la enfermedad",
                        Toast.LENGTH_SHORT).show();
            }

            //cerrar la conexion
            bd.close();
            conexion1.close();
        }
    }
}