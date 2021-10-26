package com.example.proyectarduino.helpers;

public class Helper {

    public static final String TABLA_USUARIO = "usuario";
    public static final String CORREO = "correo";
    public static final String NOMBRE = "nombre";
    public static final String PASSWORD = "password";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + ""
            + TABLA_USUARIO + "(" +
            CORREO + " TEXT, " +
            NOMBRE + " TEXT, " +
            PASSWORD + " TEXT)";

}
