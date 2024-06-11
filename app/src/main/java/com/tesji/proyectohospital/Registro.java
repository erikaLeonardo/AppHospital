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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tesji.proyectohospital.model.ConexionSQLite;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    Button cerrar;
    Button concul;
    Button actuAliRE;
    Button citasMedicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cerrar = findViewById(R.id.cerrarR);
        concul = findViewById(R.id.consuRegi);
        actuAliRE = findViewById(R.id.actuaRegi);
        citasMedicas = findViewById(R.id.citasRegi);

        citasMedicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent verreg = new Intent(Registro.this, RegistrarRegistros.class);
                startActivity(verreg);
            }
        });

        actuAliRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent actreg = new Intent(Registro.this, ActualizarRegistros.class);
                startActivity(actreg);

            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        concul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verreg = new Intent(Registro.this, VerRegistros.class);
                startActivity(verreg);
            }
        });
    }
}