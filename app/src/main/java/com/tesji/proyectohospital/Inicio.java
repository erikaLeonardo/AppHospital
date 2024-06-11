package com.tesji.proyectohospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button pacientes;
    Button enfermedades1;
    Button registros;
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        pacientes = findViewById(R.id.pacientesI);
        enfermedades1 = findViewById(R.id.enfer);
        registros = findViewById(R.id.registro);
        cerrar = findViewById(R.id.cerrarI);

        pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pacientes = new Intent(Inicio.this, Pacientes.class);
                startActivity(pacientes);
            }
        });

        enfermedades1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enfermedades = new Intent(Inicio.this, Enfermedad.class);
                startActivity(enfermedades);
            }
        });

        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registross = new Intent(Inicio.this, Registro.class);
                startActivity(registross);
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}