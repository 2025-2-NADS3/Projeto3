package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Tela principal que agora herda de BaseActivity para ter a navegação unificada.
 */
public class MainMenuActivity extends BaseActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        dbHelper = new DatabaseHelper(this);

        EditText searchBar = findViewById(R.id.search_bar);
        TextView tvVerMaisBebidas = findViewById(R.id.tv_ver_mais_bebidas);
        TextView tvVerMaisMarmitinhas = findViewById(R.id.tv_ver_mais_marmitinhas);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // **LÓGICA DE NAVEGAÇÃO CENTRALIZADA**
        // Apenas uma linha para configurar toda a barra de navegação.
        setupBottomNavigation(bottomNavigationView, R.id.nav_home);

        setupRecyclerViews();
        setupClickListeners(tvVerMaisBebidas, tvVerMaisMarmitinhas);
        setupSearch(searchBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecyclerViews();
    }

    private void setupRecyclerViews() {
        // ... (código existente, sem alterações)
        List<Produto> allProducts = dbHelper.getAllProdutos();
        List<Produto> recomendadosList = filterByCategory(allProducts, "Salgados");
        List<Produto> bebidasList = filterByCategory(allProducts, "Bebidas Geladas");
        List<Produto> marmitasList = filterByCategory(allProducts, "Queridinhos");

        RecyclerView rvRecomendados = findViewById(R.id.rv_recomendados);
        rvRecomendados.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecomendados.setAdapter(new ProductAdapter(recomendadosList));

        RecyclerView rvBebidas = findViewById(R.id.rv_bebidas_main);
        rvBebidas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvBebidas.setAdapter(new ProductAdapter(bebidasList));

        RecyclerView rvMarmitas = findViewById(R.id.rv_marmitas_main);
        rvMarmitas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvMarmitas.setAdapter(new ProductAdapter(marmitasList));
    }

    private List<Produto> filterByCategory(List<Produto> products, String category) {
        List<Produto> filteredList = new ArrayList<>();
        for (Produto product : products) {
            if (product.getCategoria() != null && product.getCategoria().equalsIgnoreCase(category)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    // Listener de navegação foi removido daqui e centralizado na BaseActivity
    private void setupClickListeners(TextView tvVerMaisBebidas, TextView tvVerMaisMarmitinhas) {
        tvVerMaisBebidas.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, CategoryActivity.class);
            intent.putExtra(CategoryActivity.EXTRA_CATEGORY_NAME, "Bebidas Geladas");
            startActivity(intent);
        });

        tvVerMaisMarmitinhas.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, CategoryActivity.class);
            intent.putExtra(CategoryActivity.EXTRA_CATEGORY_NAME, "Queridinhos");
            startActivity(intent);
        });
    }

    private void setupSearch(EditText searchBar) {
        // ... (código existente, sem alterações)
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String query = searchBar.getText().toString().trim();
                if (!query.isEmpty()) {
                    Intent intent = new Intent(MainMenuActivity.this, SearchResultsActivity.class);
                    intent.putExtra(SearchResultsActivity.EXTRA_QUERY, query);
                    startActivity(intent);
                }
                return true;
            }
            return false;
        });
    }
}
