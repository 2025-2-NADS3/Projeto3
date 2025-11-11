package com.example.meuapp; // SUBSTITUA PELO SEU PACOTE

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordCheckEmailActivity extends AppCompatActivity {

    private Button btnAvancarCheckEmail;
    private TextView txtReenviarEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_check_email);

        btnAvancarCheckEmail = findViewById(R.id.btnAvancarCheckEmail);
        txtReenviarEmail = findViewById(R.id.txtReenviarEmail);

        btnAvancarCheckEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: A lógica aqui depende do seu fluxo.
                // Se o usuário precisa inserir um código OTP, vá para ConfirmOtpActivity.
                // Se o email contém um link direto para resetar a senha,
                // esta tela pode apenas fechar e o usuário abriria o link.
                // Por agora, vamos para a tela de reset de senha (que assumiria que o usuário
                // tem um token/código ou clicou em um link que o levou para lá).
                Intent intent = new Intent(ForgotPasswordCheckEmailActivity.this, ResetPasswordActivity.class);
                // Você pode precisar passar algum token ou código que a API retornaria ou estaria no link
                // intent.putExtra("RESET_TOKEN", "seu_token_aqui");
                startActivity(intent);
            }
        });

        txtReenviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Adicionar lógica para chamar API/Backend para REENVIAR email de redefinição
                Toast.makeText(ForgotPasswordCheckEmailActivity.this, "Email de redefinição reenviado.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
