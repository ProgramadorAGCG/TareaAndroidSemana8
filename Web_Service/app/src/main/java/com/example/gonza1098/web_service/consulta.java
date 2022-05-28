package com.example.gonza1098.web_service;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gonza1098.web_service.entidades.Alumno;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class consulta extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText et_codigo;
    TextView tv_consulta;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        et_codigo=(EditText)findViewById(R.id.txt_consultacodigo);
        tv_consulta=(TextView)findViewById(R.id.txt_consulta);

    request= Volley.newRequestQueue(this);

    }

    public void consulta(View view){

    cargarWebService();
    }

    private void cargarWebService(){
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando....");
        progreso.show();

        String url="http://192.168.1.2:8081/WebService/wsJSONConsultarUsuario.php?codigo="+
                et_codigo.getText().toString();
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }


    @Override
    public void onResponse(JSONObject response) {

        progreso.hide();
        Toast.makeText(this,"Mesaje:"+response,Toast.LENGTH_SHORT).show();

        Alumno mialumno=new Alumno();
        JSONArray json=response.optJSONArray("alumno");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            mialumno.setNombre(jsonObject.optString("nombre"));
            mialumno.setPromedio(jsonObject.optString("promedio"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_consulta.setText(mialumno.getNombre()+"    "+mialumno.getPromedio());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"No se pudo Consultar"+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }


}
