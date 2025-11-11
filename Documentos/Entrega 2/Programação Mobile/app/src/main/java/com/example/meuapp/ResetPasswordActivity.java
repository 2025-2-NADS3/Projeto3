package com.example.meuapp; // SUBSTITUA PELO SEU PACOTE

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText edtNovaSenha, edtConfirmarNovaSenha;
    private Button btnConfirmarNovaSenha;    private String resetToken; // Para armazenar o token recebido da activity anterior ou do deep link

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        edtNovaSenha = findViewById(R.id.edtNovaSenha);
        edtConfirmarNovaSenha = findViewById(R.id.edtConfirmarNovaSenha);
        btnConfirmarNovaSenha = findViewById(R.id.btnConfirmarNovaSenha);

        // TODO: Obter o token de reset (se aplicável)
        // resetToken = getIntent().getStringExtra("RESET_TOKEN");
        // if (resetToken == null) {
        //    Toast.makeText(this, "Token de reset inválido.", Toast.LENGTH_LONG).show();
        //    finish(); // Fecha a activity se não houver token
        //    return;
        // }

        btnConfirmarNovaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novaSenha = edtNovaSenha.getText().toString().trim();
                String confirmarSenha = edtConfirmarNovaSenha.getText().toString().trim();

                if (TextUtils.isEmpty(novaSenha) || TextUtils.isEmpty(confirmarSenha)) {
                    Toast.makeText(ResetPasswordActivity.this, "Por favor, preencha ambos os campos de senha.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!novaSenha.equals(confirmarSenha)) {
                    Toast.makeText(ResetPasswordActivity.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
                    edtConfirmarNovaSenha.setError("As senhas não coincidem");
                    edtConfirmarNovaSenha.requestFocus();
                    return;
                }

                // TODO: Adicionar validação de força da senha (mínimo de caracteres, etc.)

                // TODO: Adicionar lógica para chamar API/Backend para alterar a senha
                // usando o 'resetToken' (se aplicável) e a 'novaSenha'.
                // Por agora, vamos simular o sucesso.
                Toast.makeText(ResetPasswordActivity.this, "Senha sendo alterada...", Toast.LENGTH_SHORT).show();

                // Simulando sucesso e indo para a tela de confirmação
                // Na realidade, você só iria para a próxima tela após a confirmação da API
                Intent intent = new Intent(ResetPasswordActivity.this, PasswordChangedSuccessActivity.class);
                startActivity(intent);
                finishAffinity(); // Fecha todas as activities anteriores deste fluxo
            }
        });
    }
}
