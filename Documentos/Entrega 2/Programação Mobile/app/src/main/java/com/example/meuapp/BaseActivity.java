package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * PROJETO: MeuApp (Comedoria da Tia)
 * ALUNO: [Seu Nome Aqui]
 *
 * DESCRIÇÃO:
 * Criei esta BaseActivity para centralizar a lógica da barra de navegação inferior.
 * Todas as minhas outras activities que têm essa barra vão herdar (extends) desta classe.
 * Isso evita código duplicado e torna a manutenção muito mais fácil (princípio DRY - Don't Repeat Yourself).
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Este método configura a BottomNavigationView.
     * Ele encontra a view, define qual item deve estar selecionado e configura o listener de clique.
     *
     * @param bottomNavView A instância da BottomNavigationView no layout da activity filha.
     * @param selectedItemId O ID do item de menu que deve ser marcado como ativo.
     */
    protected void setupBottomNavigation(BottomNavigationView bottomNavView, int selectedItemId) {
        // Marca o item correto como selecionado.
        bottomNavView.setSelectedItemId(selectedItemId);

        // Configura o listener para tratar os cliques nos itens da navegação.
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // Se o usuário clicar no item da tela atual, não faz nada (return true).
            if (itemId == selectedItemId) {
                return true;
            }

            // Navega para a activity correspondente ao item clicado.
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                // Adiciona flags para limpar o histórico e evitar telas empilhadas.
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.nav_cart) {
                startActivity(new Intent(getApplicationContext(), CarrinhoActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
    }
}
