package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Tela de Perfil, que agora herda de BaseActivity para ter a navegação unificada.
 */
public class PerfilActivity extends BaseActivity {

    private TextView tvUserName, tvUserEmail;
    private Button btnSair;
    private SessionManager sessionManager;
    private DatabaseHelper dbHelper;
    private LinearLayout optionFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        sessionManager = SessionManager.getInstance(this);
        dbHelper = new DatabaseHelper(this);

        tvUserName = findViewById(R.id.tv_user_name);
        tvUserEmail = findViewById(R.id.tv_user_email);
        btnSair = findViewById(R.id.btn_sair);
        optionFeedback = findViewById(R.id.option_feedback);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_perfil);

        // **LÓGICA DE NAVEGAÇÃO CENTRALIZADA**
        setupBottomNavigation(bottomNavigationView, R.id.nav_profile);

        loadUserProfile();

        btnSair.setOnClickListener(v -> logout());

        optionFeedback.setOnClickListener(v -> sendFeedbackEmail());

        // O listener da bottom navigation foi removido daqui e centralizado na BaseActivity.
    }

    private void loadUserProfile() {
        String userEmail = sessionManager.getLoggedInUserEmail();
        if (userEmail != null) {
            Usuario user = dbHelper.getUserByEmail(userEmail);
            if (user != null) {
                tvUserName.setText(user.getNome());
                tvUserEmail.setText(user.getEmail());
            } else {
                Toast.makeText(this, "Erro ao carregar dados do usuário.", Toast.LENGTH_SHORT).show();
                logout();
            }
        } else {
            logout();
        }
    }

    private void logout() {
        sessionManager.logoutUser();
        Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void sendFeedbackEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"seuemaildefeedback@exemplo.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback do App Comedoria da Tia");
        try {
            startActivity(Intent.createChooser(intent, "Enviar feedback via..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Não há aplicativos de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}
