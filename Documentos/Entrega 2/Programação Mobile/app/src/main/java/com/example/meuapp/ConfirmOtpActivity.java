package com.example.meuapp; // SUBSTITUA PELO SEU PACOTE

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOtpActivity extends AppCompatActivity {

    private EditText edtOtpCode;
    private Button btnConfirmarCodigo; // Renomeei o botão para refletir a ação

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_otp); // Certifique-se que o ID do botão no XML é btnReceberSenhaConfirmOtp ou mude aqui

        edtOtpCode = findViewById(R.id.edtOtpCode);
        // O ID do botão no seu XML era 'btnReceberSenhaConfirmOtp'. Se o propósito é confirmar o OTP,
        // o ID e o texto deveriam ser diferentes. Vou assumir que você ajustará o XML ou o ID aqui.
        // Se o ID no XML é 'btnReceberSenhaConfirmOtp' e você quer manter:
        // btnConfirmarCodigo = findViewById(R.id.btnReceberSenhaConfirmOtp);
        // Mas o texto "Receber Senha" seria confuso.
        // Assumindo que você renomearia o botão no XML para algo como 'btnConfirmarOtpCode'
        btnConfirmarCodigo = findViewById(R.id.btnReceberSenhaConfirmOtp); // MANTENDO O ID DO XML POR ENQUANTO
        btnConfirmarCodigo.setText("Confirmar Código"); // Mudando o texto programaticamente como exemplo

        btnConfirmarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = edtOtpCode.getText().toString().trim();

                if (TextUtils.isEmpty(otp) || otp.length() < 4) { // Assumindo OTP de pelo menos 4 dígitos
                    Toast.makeText(ConfirmOtpActivity.this, "Por favor, insira um código OTP válido.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Adicionar lógica para chamar API/Backend para verificar o código OTP.
                // Se o OTP for válido, a API geralmente permitiria prosseguir com o reset ou
                // forneceria um token para a próxima etapa (ResetPasswordActivity).
                // Por agora, vamos simular sucesso e ir para ResetPasswordActivity.

                Toast.makeText(ConfirmOtpActivity.this, "Código OTP verificado (simulação).", Toast.LENGTH_SHORT).show();

                // Se este passo VIER ANTES de ResetPasswordActivity:
                Intent intent = new Intent(ConfirmOtpActivity.this, ResetPasswordActivity.class);
                intent.putExtra("RESET_TOKEN", "token_obtido_apos_otp_valido_se_necessario");
                startActivity(intent);
                finish();

                // Se este passo for para CONFIRMAR a nova senha (cenário menos comum para OTP):
                // Intent intent = new Intent(ConfirmOtpActivity.this, PasswordChangedSuccessActivity.class);
                // startActivity(intent);
                // finishAffinity();
            }
        });
    }
}
