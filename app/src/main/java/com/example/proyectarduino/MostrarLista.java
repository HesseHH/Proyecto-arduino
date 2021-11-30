package com.example.proyectarduino;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.se.omapi.Session;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectarduino.helpers.Helper;
import com.example.proyectarduino.models.Usuario;

import java.net.ConnectException;
import java.util.ArrayList;

public class MostrarLista extends AppCompatActivity {
    ListView listCuentas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuario;

    ConnectionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_users",null,1);
        listCuentas = (ListView) findViewById(R.id.listCuentas);
        consultarListaCuentas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listCuentas.setAdapter(adaptador);

    }

    private void consultarListaCuentas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        listaUsuario = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Helper.TABLA_USUARIO,null);

        while (cursor.moveToNext()) {
            usuario = new Usuario();
            usuario.setNombre(cursor.getString(0));
            usuario.setEmail(cursor.getString(1));

            listaUsuario.add(usuario);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaUsuario.size(); i++){
            listaInformacion.add(listaUsuario.get(i).getNombre() +" - "
                    +listaUsuario.get(i).getEmail());
        }
    }
}