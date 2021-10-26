package com.example.proyectarduino.session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectarduino.R;

public class RecoveryAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_account);
    }

    public void onClick(View view) {
        Intent intent = new Intent(RecoveryAccountActivity.this, LoginActivity.class);
        Toast.makeText(getApplicationContext(), "Correo enviado", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}