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

import java.nio.charset.StandardCharsets;

public class Enfermedad extends AppCompatActivity {

    Button regisEn;
    Button cerrarE;
    Button actualiE;
    Button consultarE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedad);

        regisEn = findViewById(R.id.regE);
        consultarE = findViewById(R.id.consuE);
        actualiE = findViewById(R.id.actE);
        cerrarE = findViewById(R.id.cerE);

        regisEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verenfe = new Intent(Enfermedad.this, RegistroEnfer.class);
                startActivity(verenfe);
            }
        });

        cerrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        consultarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verenfe = new Intent(Enfermedad.this, VerEnfermedades.class);
                startActivity(verenfe);
            }
        });

        actualiE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actEnfermedad = new Intent(Enfermedad.this, actualizarEnfe.class);
                startActivity(actEnfermedad);
            }
        });

    }
}