package com.example.proyectarduino.models;

public class Usuario implements java.io.Serializable {

    private String email;
    private String nombre;
    private String password;

    public Usuario(){}

    public Usuario(String email, String nombre, String password){
        this.email = email;
        this.nombre = nombre;
        this.password = password;
    }

}
