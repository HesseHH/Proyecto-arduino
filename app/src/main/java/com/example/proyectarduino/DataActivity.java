package com.example.proyectarduino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectarduino.helpers.Helper;

public class DataActivity extends AppCompatActivity {

    private EditText email, name;
    ConnectionSQLiteHelper connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        connection = new ConnectionSQLiteHelper(this, "bd_users", null, 1);

        email = (EditText) findViewById(R.id.txtEmailData);
        name = (EditText) findViewById(R.id.txtNameData);
    }

    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {

            case R.id.btn_search_user:
                searchUser();
                break;

            case R.id.btn_update_user:
                updateUser();
                break;

            case R.id.btn_delete_user:
                deleteUser();
                break;
        }
    }

    private void searchUser() {
        SQLiteDatabase db = connection.getWritableDatabase();
        String[] parameters = {email.getText().toString()};
        String[] columns = {Helper.NOMBRE};

        try {
            Cursor cursor = db.query(
                    Helper.TABLA_USUARIO,
                    columns,
                    Helper.CORREO + "=?",
                    parameters,
                    null,
                    null,
                    null);

            cursor.moveToFirst();
            name.setText(cursor.getString(0));
            System.out.println(name);
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"El correo electrónico no existe",Toast.LENGTH_LONG).show();
        }
    }

    private void updateUser() {
        SQLiteDatabase db = connection.getWritableDatabase();
        String[] parameters = {email.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Helper.NOMBRE, name.getText().toString());

        db.update(Helper.TABLA_USUARIO, values, Helper.CORREO + "=?", parameters);
        Toast.makeText(getApplicationContext(),"Usuario Actualizado",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void deleteUser() {
        SQLiteDatabase db = connection.getWritableDatabase();
        String[] parameters = {email.getText().toString()};

        db.delete(Helper.TABLA_USUARIO, Helper.CORREO + "=?", parameters);
        Toast.makeText(getApplicationContext(),"Usuario Actualizado",Toast.LENGTH_LONG).show();
        db.close();
        email.setText("");
        name.setText("");
    }
}