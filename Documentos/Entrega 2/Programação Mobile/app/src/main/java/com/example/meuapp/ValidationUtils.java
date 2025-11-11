package com.example.meuapp;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * PROJETO: MeuApp (Comedoria da Tia)
 * ALUNO: [Seu Nome Aqui]
 *
 * DESCRIÇÃO:
 * Criei esta classe de utilitários para centralizar toda a lógica de validação do aplicativo.
 * Em vez de espalhar as regras de negócio pelas Activities, eu as coloco aqui em métodos
 * estáticos (static), o que torna o código mais limpo, organizado e reutilizável.
 *
 * Usei Regex (Expressões Regulares) para definir os padrões complexos de nome e senha.
 */
public class ValidationUtils {

    // Regex para o nome: permite letras (incluindo acentos) e espaços, de 3 a 40 caracteres.
    private static final Pattern NOME_PATTERN = Pattern.compile("^[\\p{L}\\s]{3,40}$");

    // Regex para a senha: esta é uma expressão complexa que exige todas as regras de uma só vez.
    private static final Pattern SENHA_PATTERN = Pattern.compile(
            "^(?=.*[0-9])" +         // Pelo menos 1 número
            "(?=.*[a-z])" +         // Pelo menos 1 letra minúscula
            "(?=.*[A-Z])" +         // Pelo menos 1 letra maiúscula
            "(?=.*[!@#$%&*?])" +    // Pelo menos 1 caractere especial do conjunto especificado
            "(?=\\S+$)" +          // Sem espaços em branco
            ".{8,}" +               // Pelo menos 8 caracteres de comprimento
            "$");

    /**
     * Valida o nome do usuário de acordo com o padrão profissional.
     * Retorna true se o nome for válido, false caso contrário.
     */
    public static boolean isValidName(String nome) {
        // Verifica se o nome não é nulo e se corresponde ao padrão Regex que eu defini.
        return nome != null && NOME_PATTERN.matcher(nome).matches();
    }

    /**
     * Valida o formato do email usando o padrão oficial do Android.
     * Retorna true se o email for válido, false caso contrário.
     */
    public static boolean isValidEmail(String email) {
        // Patterns.EMAIL_ADDRESS é um padrão Regex já fornecido pelo Android, o que é muito prático.
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Valida a complexidade da senha de acordo com o padrão profissional.
     * Retorna true se a senha for válida, false caso contrário.
     */
    public static boolean isValidPassword(String senha) {
        // Verifica se a senha não é nula e se corresponde ao padrão Regex de segurança que eu defini.
        return senha != null && SENHA_PATTERN.matcher(senha).matches();
    }
}
