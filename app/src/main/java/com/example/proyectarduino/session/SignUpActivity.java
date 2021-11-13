package com.example.proyectarduino.session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectarduino.ConnectionSQLiteHelper;
import com.example.proyectarduino.R;
import com.example.proyectarduino.helpers.Helper;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, pass, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.proyectarduino.R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.txtDataNombre);
        email = (EditText) findViewById(R.id.txtDataEmailSignUp);
        pass = (EditText) findViewById(R.id.txtDataPasswordSignUp);
        confirmPass = (EditText) findViewById(R.id.txtDataConfirmPasswordSignUp);
    }

    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btnDataCreateAccount:
                if (verifyPasswords()) {
                    registerAccount();
                    intent = new Intent(SignUpActivity.this, LoginActivity.class);
                }else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGoSearchData:
                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }

    }

    private boolean verifyPasswords() {
        if (pass.getText().toString().equals(confirmPass.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }

    private void registerAccount() {

        ConnectionSQLiteHelper connection = new ConnectionSQLiteHelper(this, "bd_users", null, 1);
        SQLiteDatabase db = connection.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Helper.CORREO, email.getText().toString());
        values.put(Helper.NOMBRE, name.getText().toString());
        values.put(Helper.PASSWORD, pass.getText().toString());

        Long id = db.insert(Helper.TABLA_USUARIO, Helper.CORREO, values);

        Toast.makeText(getApplicationContext(), "Cuenta creada", Toast.LENGTH_SHORT).show();

        db.close();

    }

}