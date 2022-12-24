package com.example.lessplastic;

import android.os.Parcelable;

import java.io.Serializable;

public class Plastico implements Serializable {

    private int id;
    private String tipo;
    private int cantidad;
    private float tamaño;
    private float peso;

    public Plastico(int id, String tipo, int cantidad, float tamaño, float peso) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.tamaño = tamaño;
        this.peso = peso;
    }

    public Plastico(int id) {

        this.id = id;
        this.tipo = "";
        this.cantidad = 0;
        this.tamaño = 0;
        this.peso = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int nuevoId) {
        this.id = nuevoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String nuevoTipo) {
        this.tipo = nuevoTipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float nuevoTamaño) {
        this.tamaño = nuevoTamaño;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float nuevoPeso) {
        this.peso = nuevoPeso;
    }

    @Override
    public String toString() {
        return "Plastico{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", tamaño='" + tamaño + '\'' +
                ", peso='" + peso + '\'' +
                '}';
    }
}