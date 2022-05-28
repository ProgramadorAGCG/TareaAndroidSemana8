package com.example.tareasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Empleado extends AppCompatActivity {
    EditText txtEmpCod,txtDisCod,txtEmpApe,txtEmpNom,txtEmpDir,txtEmpTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);
        txtEmpCod=findViewById(R.id.txtcodemp);
        txtDisCod=findViewById(R.id.txtcoddis);
        txtEmpApe=findViewById(R.id.txtape);
        txtEmpNom=findViewById(R.id.txtnom);
        txtEmpDir=findViewById(R.id.txtdir);
        txtEmpTel=findViewById(R.id.txttel);
    }
    public void ingresar(View view){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codemp=Integer.parseInt(txtEmpCod.getText().toString());
            int coddis=Integer.parseInt(txtDisCod.getText().toString());
            String nom=txtEmpNom.getText().toString();
            String ape=txtEmpApe.getText().toString();
            String dir=txtEmpDir.getText().toString();
            int tel=Integer.parseInt(txtEmpTel.getText().toString());
            ContentValues registro = new ContentValues();
            registro.put("emp_cod",codemp);
            registro.put("dis_cod",coddis);
            registro.put("emp_ape",ape);
            registro.put("emp_nom",nom);
            registro.put("emp_dir",dir);
            registro.put("emp_tel",tel);
            sqlitedb.insert("empleado",null,registro);
            Toast.makeText(this, "Registro insertado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al insertar. "+e, Toast.LENGTH_SHORT).show();
        }finally {
            sqlitedb.close();
        }
    }
    public void eliminar(View view){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codemp=Integer.parseInt(txtEmpCod.getText().toString());
            sqlitedb.delete("empleado","emp_cod="+codemp,null);
            Toast.makeText(this, "Registro eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al eliminar. "+e, Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }
    }
    public void modificar(View view){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codemp = Integer.parseInt(txtEmpCod.getText().toString());
            int coddis = Integer.parseInt(txtDisCod.getText().toString());
            String nom = txtEmpNom.getText().toString();
            String ape = txtEmpApe.getText().toString();
            String dir = txtEmpDir.getText().toString();
            int tel = Integer.parseInt(txtEmpTel.getText().toString());
            ContentValues registro = new ContentValues();
            registro.put("emp_cod", codemp);
            registro.put("dis_cod", coddis);
            registro.put("emp_ape", ape);
            registro.put("emp_nom", nom);
            registro.put("emp_dir", dir);
            registro.put("emp_tel", tel);
            sqlitedb.update("empleado", registro, "emp_cod=" + codemp, null);
            Toast.makeText(this, "Registro actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al actualizar. ", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }
    }
    public void consultar(View v){
        Datos datos=new Datos(this,"dbempleado",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codemp=Integer.parseInt(txtEmpCod.getText().toString());
            Cursor fila=sqlitedb.rawQuery("select dis_cod,emp_ape,emp_nom,emp_dir,emp_tel from empleado where emp_cod="+codemp,null);
            if(fila.moveToFirst()){
                txtDisCod.setText(fila.getString(0));
                txtEmpApe.setText(fila.getString(1));
                txtEmpNom.setText(fila.getString(2));
                txtEmpDir.setText(fila.getString(3));
                txtEmpTel.setText(fila.getString(4));
                Toast.makeText(this, "Registro consultado satisfactoriamente", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "No existe el código consultado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al consultar", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }

    }
}
