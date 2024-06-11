package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pacientes extends AppCompatActivity {

    Button alta1;
    Button actualizar1;
    Button cerrar1;
    Button consulta1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        alta1 = findViewById(R.id.altaP);
        actualizar1 = findViewById(R.id.actualizarR);
        cerrar1 = findViewById(R.id.cerrarR);
        consulta1 = findViewById(R.id.consultarP);


        alta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iralta = new Intent(Pacientes.this, Alta.class);
                startActivity(iralta);
            }
        });

        actualizar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irActualizar = new Intent(Pacientes.this, Actualizar.class);
                startActivity(irActualizar);
            }
        });

        consulta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verpas = new Intent(Pacientes.this, VerPacientes.class);
                startActivity(verpas);
            }
        });

        cerrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}