package com.example.meuapp; // SUBSTITUA PELO SEU PACOTE

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordChangedSuccessActivity extends AppCompatActivity {

    private Button btnEntrarPasswordChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_changed_success);

        btnEntrarPasswordChanged = findViewById(R.id.btnEntrarPasswordChanged);

        btnEntrarPasswordChanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para a tela de Login
                Intent intent = new Intent(PasswordChangedSuccessActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Fecha esta activity
            }
        });
    }
}
