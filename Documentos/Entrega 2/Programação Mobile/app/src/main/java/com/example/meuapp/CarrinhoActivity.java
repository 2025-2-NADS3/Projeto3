package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Locale;

/**
 * Tela do Carrinho, que agora herda de BaseActivity para ter a navegação unificada.
 */
public class CarrinhoActivity extends BaseActivity {

    private RecyclerView rvCarrinho;
    private CarrinhoAdapter adapter;
    private List<CarrinhoItem> carrinhoItemList;
    private TextView tvTotalPreco;
    private TextView tvCarrinhoVazio;
    private double totalValor = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_carrinho);
        rvCarrinho = findViewById(R.id.rv_carrinho);
        Button btnAvancar = findViewById(R.id.btn_avancar);
        tvTotalPreco = findViewById(R.id.tv_total_preco);
        tvCarrinhoVazio = findViewById(R.id.tv_carrinho_vazio);

        // **LÓGICA DE NAVEGAÇÃO CENTRALIZADA**
        setupBottomNavigation(bottomNavigationView, R.id.nav_cart);

        rvCarrinho.setLayoutManager(new LinearLayoutManager(this));

        carrinhoItemList = CarrinhoManager.getInstance().getItems();
        adapter = new CarrinhoAdapter(carrinhoItemList);
        rvCarrinho.setAdapter(adapter);

        btnAvancar.setOnClickListener(v -> {
            if (carrinhoItemList.isEmpty()) {
                Toast.makeText(this, "Seu carrinho está vazio.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(CarrinhoActivity.this, FormaPagamentoActivity.class);
            intent.putExtra(FormaPagamentoActivity.EXTRA_TOTAL_VALOR, totalValor);
            startActivity(intent);
        });

        // O listener de dados do adapter agora está em onResume
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotal();
        // Garante que a UI reaja a mudanças no carrinho (ex: remoção)
        adapter.notifyDataSetChanged();
    }

    private void updateTotal() {
        totalValor = 0;
        for (CarrinhoItem item : carrinhoItemList) {
            String priceString = item.getPreco().replace("R$", "").replace(",", ".").trim();
            try {
                totalValor += Double.parseDouble(priceString) * item.getQuantidade();
            } catch (NumberFormatException e) { /* Ignora */ }
        }
        tvTotalPreco.setText(String.format(Locale.getDefault(), "R$ %.2f", totalValor));

        if (carrinhoItemList.isEmpty()) {
            tvCarrinhoVazio.setVisibility(View.VISIBLE);
            rvCarrinho.setVisibility(View.GONE);
        } else {
            tvCarrinhoVazio.setVisibility(View.GONE);
            rvCarrinho.setVisibility(View.VISIBLE);
        }
    }
}
