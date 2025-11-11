package com.example.meuapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * PROJETO: MeuApp (Comedoria da Tia)
 * ALUNO: [Seu Nome Aqui]
 *
 * DESCRIÇÃO:
 * Criei esta classe para gerenciar a sessão do usuário.
 * Ela usa SharedPreferences, que é o "porta-luvas" do Android, ideal para guardar
 * informações pequenas e persistentes, como o email do usuário logado.
 * 
 * O padrão Singleton garante que teremos apenas UMA instância deste gerenciador
 * em todo o aplicativo, evitando conflitos.
 */
public class SessionManager {

    private static SessionManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USER_EMAIL = "userEmail";

    // O construtor é privado e recebe o contexto do aplicativo para inicializar o SharedPreferences.
    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // O método público para obter a única instância da classe.
    public static synchronized SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Salva o email do usuário na sessão após o login.
     */
    public void createUserSession(String email) {
        editor.putString(KEY_USER_EMAIL, email);
        editor.commit(); // Salva a alteração.
    }

    /**
     * Busca o email do usuário que está salvo na sessão.
     * Retorna null se ninguém estiver logado.
     */
    public String getLoggedInUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    /**
     * Limpa a sessão, efetivamente fazendo o logout do usuário.
     */
    public void logoutUser() {
        editor.clear(); // Remove todos os dados da sessão.
        editor.commit(); // Salva a alteração.
    }
}
