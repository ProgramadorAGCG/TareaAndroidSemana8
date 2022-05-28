package com.example.gonza1098.web_service;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        String valores=getIntent().getStringExtra("valores_persona");
        String[] separated = valores.split("-");

        Toast.makeText(getApplicationContext(),"selecciono alumno "+separated[0]+ "\ncon codigo "+separated[1]+"\ncon promedio "+separated[2],Toast.LENGTH_LONG).show();

    }
}
