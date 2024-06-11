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

public class ActualizarRegistros extends AppCompatActivity {

    Button buscAR;
    Button guardAR;
    Button nuevoAR;
    Button eliminaAR;
    Button cerrarAR;
    TextInputEditText idR;
    TextInputEditText idPaR;
    TextInputEditText idEnR;
    TextInputEditText fecAR;

    private int listaRegi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_registros);

        buscAR = findViewById(R.id.buscarAcR);
        guardAR = findViewById(R.id.guarAR);
        nuevoAR = findViewById(R.id.nuevAR);
        eliminaAR = findViewById(R.id.eliAR);
        cerrarAR = findViewById(R.id.cerrAR);
        idR = findViewById(R.id.idRegist);
        idPaR = findViewById(R.id.idPaR);
        idEnR = findViewById(R.id.idEnR);
        fecAR = findViewById(R.id.fechaAR);


        buscAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buscarRegistro();

            }
        });

        guardAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarRegistros.this);
                builder.setMessage("Esta seguro de actualizar el registro")
                        .setPositiveButton("SI",new DialogInterface.OnClickListener(){
                            @Override
                            public  void onClick(DialogInterface dialog ,int which){
                                actualizarRegistro();
                            }
                        })
                        .setNegativeButton("CANCELAR",null);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        nuevoAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idR.setText("");
                idPaR.setText("");
                idEnR.setText("");
            }
        });

        eliminaAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarRegistros.this);
                builder.setMessage("Esta seguro de eliminar el registro")
                        .setPositiveButton("SI",new DialogInterface.OnClickListener(){
                            @Override
                            public  void onClick(DialogInterface dialog ,int which){
                                eliminarRegistro();
                            }
                        })
                        .setNegativeButton("CANCELAR",null);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        cerrarAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

    private void buscarRegistro() {
        // conecter la DB
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion1.getReadableDatabase();
        String codigo = idR.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor cursor = bd.rawQuery("SELECT idpaciente, idenfermedad, fecha FROM registro WHERE idregistro='" + codigo + "'", null);
            if (cursor.moveToFirst()) {
                idPaR.setText(cursor.getString(0));
                idEnR.setText(cursor.getString(1));
                fecAR.setText(cursor.getString(2));

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

    private void eliminarRegistro() {
        // conecter la DB
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion1.getReadableDatabase();
        String idregistro = idR.getText().toString();

        if (!idregistro.isEmpty()) {
            listaRegi = bd.delete("registro", "idregistro=" + idregistro, null);
            bd.close();
            idR.setText("");
            idPaR.setText("");
            idEnR.setText("");
            fecAR.setText("");

            if (listaRegi > 0) {
                Toast.makeText(this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ingrese el id de la enfermedad", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarRegistro() {
        // conecter la DB
        ConexionSQLite conexion1 = new ConexionSQLite(this, "Hospital", null, 1);
        //dar permisos a la base de datos
        SQLiteDatabase bd = conexion1.getWritableDatabase();

        //extraer valores del formulario
        String idregistro = idR.getText().toString();
        String idpaciente = idPaR.getText().toString();
        String idenfermedad = idEnR.getText().toString();
        String fechaAc = fecAR.getText().toString();


        if (!idregistro.isEmpty() && !idpaciente.isEmpty() && !idenfermedad.isEmpty() && !fechaAc.isEmpty() ) {
            //almacenar los registros en un contenedor para despues mandarlo a la base de datos
            ContentValues registro = new ContentValues();

            registro.put("idpaciente", idpaciente);
            registro.put("idenfermedad", idenfermedad);
            registro.put("fecha", fechaAc);

            Cursor cursor = bd.rawQuery("SELECT idpaciente || ' ' || idenfermedad || ' ' || fecha FROM registro " +
                    "WHERE idregistro='" + idregistro + "'", null);
            if (cursor.moveToFirst()) {
                listaRegi = bd.update("registro", registro, "idenfermedad=" + idregistro, null);
                if(listaRegi>0){
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