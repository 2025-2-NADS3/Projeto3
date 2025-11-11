package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnEntrar;
    private TextView txtTabCreateAccount;
    private TextView txtForgotPassword;
    private DatabaseHelper dbHelper;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        sessionManager = SessionManager.getInstance(this);

        // Se o usuário já estiver logado, vai direto para a tela principal
        if (sessionManager.getLoggedInUserEmail() != null) {
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
            return; // Impede que o resto do onCreate seja executado
        }

        edtEmail = findViewById(R.id.edtEmailLogin);
        edtSenha = findViewById(R.id.edtSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtTabCreateAccount = findViewById(R.id.txtTabCreateAccount);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        setupClickListeners();
    }

    private void setupClickListeners() {
        txtTabCreateAccount.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(i);
        });

        txtForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordEmailActivity.class);
            startActivity(intent);
        });

        btnEntrar.setOnClickListener(v -> realizarLogin());
    }

    private void realizarLogin() {
        String email = edtEmail.getText().toString().trim().toLowerCase();
        String senha = edtSenha.getText().toString().trim();

        if (!ValidationUtils.isValidEmail(email)) {
            edtEmail.setError("Formato de email inválido.");
            edtEmail.requestFocus();
            return;
        }

        if (senha.isEmpty()) {
            edtSenha.setError("Senha é obrigatória.");
            edtSenha.requestFocus();
            return;
        }

        if (dbHelper.checkUser(email, senha)) {
            // SUCESSO: Salva a sessão
            sessionManager.createUserSession(email);

            Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Credenciais inválidas. Verifique seu email e senha.", Toast.LENGTH_SHORT).show();
        }
    }
}
