package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Distrito extends AppCompatActivity {
    EditText txtdisCod,txtdisNom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distrito);
        txtdisCod=findViewById(R.id.txtcoddis);
        txtdisNom=findViewById(R.id.txtnomdis);
    }
    public void ingresar(View view){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int coddis=Integer.parseInt(txtdisCod.getText().toString());
            String nomdis=txtdisNom.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("dis_cod",coddis);
            registro.put("dis_nom",nomdis);
            sqlitedb.insert("distrito",null,registro);
            Toast.makeText(this, "Registro insertado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al insertar", Toast.LENGTH_SHORT).show();
        }finally {
            sqlitedb.close();
        }
    }
    public void eliminar(View view){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int coddis=Integer.parseInt(txtdisCod.getText().toString());
            sqlitedb.delete("distrito","dis_cod="+coddis,null);
            Toast.makeText(this, "Registro eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al eliminar", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }
    }
    public void modificar(View view){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int coddis=Integer.parseInt(txtdisCod.getText().toString());
            String nomdis=txtdisNom.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("dis_cod",coddis);
            registro.put("dis_nom",nomdis);
            sqlitedb.update("distrito",registro,"dis_cod="+coddis,null);
            Toast.makeText(this, "Registro actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al actualizar", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }

    }
    public void consultar(View v){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        int coddis=Integer.parseInt(txtdisCod.getText().toString());
        Cursor fila=sqlitedb.rawQuery("select dis_cod,dis_nom from distrito where dis_cod="+coddis,null);
        if(fila.moveToFirst()){
            txtdisNom.setText(fila.getString(1));
            Toast.makeText(this, "Registro consultado satisfactoriamente", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "No existe el código consultado", Toast.LENGTH_SHORT).show();
            sqlitedb.close();
    }


}
