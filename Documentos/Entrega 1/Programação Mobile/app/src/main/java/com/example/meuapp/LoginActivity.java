package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Mantenha este import
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnEntrar;
    // private TextView btnIrCadastro; // Linha antiga comentada ou removida
    private TextView txtTabCreateAccount; // Nova declaração para a aba "Criar conta"
    private TextView txtForgotPassword; // Para "Esqueceu a senha?"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Certifique-se de que está usando o layout correto que contém txtTabCreateAccount
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmailLogin);
        edtSenha = findViewById(R.id.edtSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);
        // btnIrCadastro = findViewById(R.id.btnIrCadastro); // Linha antiga que causava erro
        txtTabCreateAccount = findViewById(R.id.txtTabCreateAccount); // Correção
        txtForgotPassword = findViewById(R.id.txtForgotPassword); // Adicionado para o link "Esqueceu a senha?"

        // Listener para a aba/texto de ir para a tela de cadastro
        if (txtTabCreateAccount != null) { // Boa prática verificar se a view foi encontrada
            txtTabCreateAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                    startActivity(i);
                    // Geralmente, você não finaliza a LoginActivity ao ir para o cadastro,
                    // pois o usuário pode querer voltar.
                    // finish();
                }
            });
        }

        // Listener para o link "Esqueceu a senha?" (Exemplo de como adicionar)
        if (txtForgotPassword != null) {
            txtForgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implemente a lógica para recuperação de senha aqui
                    Toast.makeText(LoginActivity.this, "Funcionalidade Esqueceu a Senha (a implementar)", Toast.LENGTH_SHORT).show();
                    // Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                    // startActivity(intent);
                }
            });
        }


        // Listener para o botão de entrar
        if (btnEntrar != null) {
            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = edtEmail.getText().toString().trim();
                    String senha = edtSenha.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        edtEmail.setError("O email é obrigatório");
                        edtEmail.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(senha)) {
                        edtSenha.setError("A senha é obrigatória");
                        edtSenha.requestFocus();
                        return;
                    }

                    // Lógica de autenticação (exemplo)
                    if (email.equals("teste@teste.com") && senha.equals("123456")) {
                        Toast.makeText(LoginActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou senha inválidos.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
