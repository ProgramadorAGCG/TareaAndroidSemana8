package com.example.gonza1098.web_service;


import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;



public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText et_nombre, et_nota1, et_nota2, et_nota3, et_codigo;
    Button btn_guardar;

    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_nombre = (EditText) findViewById(R.id.txt_nombre);
        et_nota1 = (EditText) findViewById(R.id.txt_nota1);
        et_nota2 = (EditText) findViewById(R.id.txt_nota2);
        et_nota3 = (EditText) findViewById(R.id.txt_nota3);
        btn_guardar=(Button)findViewById(R.id.btn_guardar);

        request= Volley.newRequestQueue(this);
    }

    public void grabar(View x) {
        cargarWebservice();
    }
    public void consulta(View view){
        Intent myintent=new Intent(MainActivity.this,consulta.class);
        startActivity(myintent);

    }

    private void cargarWebservice(){
        String codigo=et_codigo.getText().toString();
        String nombre=et_nombre.getText().toString();
        int nota1_int = Integer.parseInt(et_nota1.getText().toString());
        int nota2_int = Integer.parseInt(et_nota2.getText().toString());
        int nota3_int = Integer.parseInt(et_nota3.getText().toString());
        int resultado = (nota1_int + nota2_int + nota3_int) / 3;
        String promedio=String.valueOf(resultado);


        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        //se canbia la URL (Host)
        String url="http://192.168.1.2:8081/WebService/wsJSONRegistro.php?codigo="
                +codigo+"&nombre="+nombre+"&promedio="+promedio;

        //espacio
        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"Se ha Registrado exitosamente",Toast.LENGTH_SHORT).show();
        progreso.hide();
        et_codigo.setText("");
        et_nombre.setText("");
        et_nota1.setText("");
        et_nota2.setText("");
        et_nota3.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"No se pudo registrar"+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());

    }
    //compile  'com.android.volley:volley:1.1.0'


}

