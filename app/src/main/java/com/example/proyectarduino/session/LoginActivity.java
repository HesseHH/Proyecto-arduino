package com.example.proyectarduino.session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectarduino.ConnectionSQLiteHelper;
import com.example.proyectarduino.DataActivity;
import com.example.proyectarduino.R;
import com.example.proyectarduino.helpers.Helper;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    ConnectionSQLiteHelper connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.proyectarduino.R.layout.activity_login);
        connection = new ConnectionSQLiteHelper(this, "bd_users", null, 1);

        email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPassword);
    }

    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btnLogin:
                if (verifyAccount()) {
                    // TODO: mandar a pagina principal
                    System.out.println("Credenciales correctas");
                    intent = new Intent(LoginActivity.this, DataActivity.class);
                }else {
                    Toast.makeText(getApplicationContext(), "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnGoCreateAccount:
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                break;

            case R.id.btnRecoveryPassword:
                intent = new Intent(LoginActivity.this, RecoveryAccountActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }

    }

    private boolean verifyAccount() {
        SQLiteDatabase db = connection.getWritableDatabase();
        String[] parameters = {email.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " +
                    Helper.PASSWORD +
                    " FROM " + Helper.TABLA_USUARIO +
                    " WHERE " + Helper.CORREO + "=?", parameters);

            cursor.moveToFirst();

            if (pass.getText().toString().equals(cursor.getString(0))) {
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }
    }
}