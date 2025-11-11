package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtEmail, edtSenha;
    private Button btnRegistrar;
    private TextView txtLoginTab; // TextView para a aba de Login
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new DatabaseHelper(this);

        edtNome = findViewById(R.id.edtNomeCompleto);
        edtEmail = findViewById(R.id.edtEmailCadastro);
        edtSenha = findViewById(R.id.edtSenhaCadastro);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        txtLoginTab = findViewById(R.id.txtTabLoginUnselected); // ID CORRETO APLICADO

        btnRegistrar.setOnClickListener(v -> registrarUsuario());

        // Listener para a aba de Login
        txtLoginTab.setOnClickListener(v -> {
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void registrarUsuario() {
        String nome = edtNome.getText().toString().trim();
        String email = edtEmail.getText().toString().trim().toLowerCase();
        String senha = edtSenha.getText().toString().trim();

        if (!isDataValid(nome, email, senha)) {
            return;
        }

        if (dbHelper.checkUserExists(email)) {
            edtEmail.setError("Este email já está cadastrado.");
            edtEmail.requestFocus();
            return;
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);

        if (dbHelper.addUser(novoUsuario)) {
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Erro ao realizar o cadastro. Tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isDataValid(String nome, String email, String senha) {
        if (!ValidationUtils.isValidName(nome)) {
            edtNome.setError("Nome inválido. Use de 3 a 40 letras/espaços.");
            edtNome.requestFocus();
            return false;
        }

        if (!ValidationUtils.isValidEmail(email)) {
            edtEmail.setError("Formato de email inválido.");
            edtEmail.requestFocus();
            return false;
        }

        if (!ValidationUtils.isValidPassword(senha)) {
            edtSenha.setError("Senha fraca. Use 8+ caracteres, com maiúscula, minúscula, número e símbolo (!@#$%&*?)");
            edtSenha.requestFocus();
            return false;
        }

        if (senha.contains(email) && !email.isEmpty()) {
            edtSenha.setError("A senha não pode ser igual ao email.");
            edtSenha.requestFocus();
            return false;
        }
        
        return true;
    }
}
