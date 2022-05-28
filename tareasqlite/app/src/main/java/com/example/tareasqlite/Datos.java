package com.example.tareasqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Datos extends SQLiteOpenHelper {
    public Datos(Context context, String name, CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table distrito(dis_cod integer primary key, dis_nom text)");
        db.execSQL("create table empleado(emp_cod integer primary key, dis_cod integer, " +
                "emp_ape text, emp_nom text, emp_dir text,emp_tel integer, foreign key (dis_cod) references distrito(dis_cod))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
