package com.example.gonza1098.web_service.entidades;

import android.content.Intent;

import java.util.PriorityQueue;

public class Alumno {

    private Integer codigo;
    private String nombre;
    private String promedio;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }
}
