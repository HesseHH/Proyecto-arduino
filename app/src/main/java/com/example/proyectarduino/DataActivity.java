package com.example.proyectarduino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {

    EditText email, pass;
    ConnectionSQLiteHelper connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        connection = new ConnectionSQLiteHelper(this, "bd_users", null, 1);

        email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPassword);
    }
}