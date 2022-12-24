package com.example.lessplastic;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    public Usuario(int id, String nombreUsuario, String nombre, String apellido, String correo, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String password) {
        this.contraseña = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + contraseña + '\'' +
                '}';
    }
}
