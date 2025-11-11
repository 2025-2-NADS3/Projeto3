package com.example.meuapp; // SUBSTITUA PELO SEU PACOTE

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordEmailActivity extends AppCompatActivity {

    private EditText edtEmailForgot;
    private Button btnEnviarEmail;
    private TextView txtEntrarForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_email);

        edtEmailForgot = findViewById(R.id.edtEmailForgot);
        btnEnviarEmail = findViewById(R.id.btnEnviarEmail);
        txtEntrarForgotPassword = findViewById(R.id.txtEntrarForgotPassword);

        btnEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailForgot.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ForgotPasswordEmailActivity.this, "Por favor, insira seu email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // SIMPLIFICADO: Indo direto para a tela de resetar a senha.
                Intent intent = new Intent(ForgotPasswordEmailActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        txtEntrarForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para a tela de Login
                Intent intent = new Intent(ForgotPasswordEmailActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
