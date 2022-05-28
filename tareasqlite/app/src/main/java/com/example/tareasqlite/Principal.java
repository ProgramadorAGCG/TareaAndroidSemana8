package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        b1=(Button)findViewById(R.id.bEmpleado);
        b2=(Button)findViewById(R.id.bDistrito);
    }

    public void abrirEmpleado(View view) {
        startActivity(new Intent(Principal.this,Empleado.class));
    }
    public void abrirDistrito(View view) {
        startActivity(new Intent(Principal.this,Distrito.class));
    }

}
