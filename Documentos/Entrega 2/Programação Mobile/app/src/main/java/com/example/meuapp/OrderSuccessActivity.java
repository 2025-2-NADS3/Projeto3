package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class OrderSuccessActivity extends AppCompatActivity {

    public static final String EXTRA_TOTAL_VALOR = "total_valor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        TextView tvPedidoInfo = findViewById(R.id.tv_pedido_info);
        Button btnVoltar = findViewById(R.id.btn_voltar_home);

        // Pega o valor total passado pelo Intent
        double totalValor = getIntent().getDoubleExtra(EXTRA_TOTAL_VALOR, 0.0);

        // Formata e exibe a mensagem com o valor
        String infoText = String.format(Locale.getDefault(),
                "Valor total pago: R$ %.2f\n\nPode se dirigir para a cantina\n1º andar, bloco C",
                totalValor);
        tvPedidoInfo.setText(infoText);

        // Configura o botão para voltar ao menu principal
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(OrderSuccessActivity.this, MainMenuActivity.class);
            // Limpa todas as telas anteriores (carrinho, pagamento, etc.)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
