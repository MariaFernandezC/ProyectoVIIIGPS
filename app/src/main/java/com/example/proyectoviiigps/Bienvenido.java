package com.example.proyectoviiigps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Bienvenido extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        final TextView mensaje =(TextView)findViewById(R.id.mensaje);
        Intent i = this.getIntent();
        String nombre = i.getStringExtra("nombre");
        int edad = i.getIntExtra("edad", -1);
        mensaje.setText(mensaje.getText()+"  " + nombre +" Su edad:" + edad + " ");
    }
}
