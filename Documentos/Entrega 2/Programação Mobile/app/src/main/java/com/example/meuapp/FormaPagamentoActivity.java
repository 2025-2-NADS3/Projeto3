package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoActivity extends AppCompatActivity implements PaymentMethodAdapter.OnPaymentMethodClickListener {

    public static final String EXTRA_TOTAL_VALOR = "total_valor";
    private double totalValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_pagamento);

        // Pega o valor total passado pela CarrinhoActivity
        totalValor = getIntent().getDoubleExtra(EXTRA_TOTAL_VALOR, 0.0);

        ImageView ivVoltar = findViewById(R.id.iv_voltar_pagamento);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_pagamento);
        ivVoltar.setOnClickListener(v -> finish());
        bottomNavigationView.setSelectedItemId(R.id.nav_cart);

        RecyclerView rvPaymentMethods = findViewById(R.id.rv_payment_methods);
        rvPaymentMethods.setLayoutManager(new LinearLayoutManager(this));

        // Cria a lista de métodos de pagamento
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new PaymentMethod("Pix", R.drawable.pix));
        paymentMethodList.add(new PaymentMethod("Cartão de crédito", R.drawable.cartao));
        paymentMethodList.add(new PaymentMethod("Dinheiro", R.drawable.dinheiro));

        // Cria o adapter, passando a lista e a própria activity como "ouvinte" do clique
        PaymentMethodAdapter adapter = new PaymentMethodAdapter(paymentMethodList, this);
        rvPaymentMethods.setAdapter(adapter);

        // ... (código da bottom navigation) ...
    }

    /**
     * Este método é o "callback". Ele é chamado pelo adapter quando o usuário clica em "Selecionar".
     */
    @Override
    public void onPaymentMethodClick(PaymentMethod paymentMethod) {
        // 1. Limpa o carrinho de compras
        CarrinhoManager.getInstance().clearCart();

        // 2. Exibe uma mensagem de confirmação
        Toast.makeText(this, "Pagamento com " + paymentMethod.getName() + " selecionado!", Toast.LENGTH_SHORT).show();

        // 3. Navega para a tela de sucesso
        Intent intent = new Intent(this, OrderSuccessActivity.class);
        intent.putExtra(OrderSuccessActivity.EXTRA_TOTAL_VALOR, totalValor); // Passa o valor para a tela de sucesso
        startActivity(intent);
        finishAffinity(); // Fecha esta e todas as activities anteriores (carrinho)
    }
}
