package com.example.meuapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * PROJETO: MeuApp (Comedoria da Tia)
 * ALUNO: [Seu Nome Aqui]
 *
 * DESCRIÇÃO:
 * Esta é uma tela "camaleão", genérica e reutilizável, que eu criei para exibir os produtos de QUALQUER categoria.
 * Ela substituiu as antigas `BebidasActivity` e `MarmitinhasActivity`, eliminando código duplicado.
 *
 * FUNCIONALIDADES:
 * 1. Recebe o nome de uma categoria via Intent da tela que a chamou (ex: `MainMenuActivity`).
 * 2. Usa o nome da categoria para definir o título da tela, tornando a experiência do usuário clara.
 * 3. Busca no `DatabaseHelper` todos os produtos e os filtra, exibindo apenas os da categoria recebida.
 * 4. Apresenta os produtos em um layout de grade (2 colunas), o que é ótimo para visualização de itens com imagens.
 */
public class CategoryActivity extends AppCompatActivity {

    // Chave para o "pacote" de dados que o Intent vai carregar. É uma boa prática usar uma constante.
    public static final String EXTRA_CATEGORY_NAME = "category_name";
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar_category);
        setSupportActionBar(toolbar);

        // Pego o nome da categoria que a `MainMenuActivity` me enviou.
        String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

        // Configuro a barra de ferramentas (Toolbar) para exibir o nome da categoria e o botão de "Voltar".
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(categoryName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView rvCategoryProducts = findViewById(R.id.rv_category_products);
        // Escolhi um `GridLayoutManager` com 2 colunas para uma melhor apresentação visual dos produtos.
        rvCategoryProducts.setLayoutManager(new GridLayoutManager(this, 2));

        // Busco todos os produtos do banco e depois chamo meu método para filtrar.
        List<Produto> allProducts = dbHelper.getAllProdutos();
        List<Produto> productList = filterByCategory(allProducts, categoryName);

        // Uso o mesmo `ProductAdapter` reutilizável para exibir a lista filtrada.
        ProductAdapter adapter = new ProductAdapter(productList);
        rvCategoryProducts.setAdapter(adapter);
    }

    /**
     * O mesmo método de filtro robusto usado na MainMenuActivity.
     */
    private List<Produto> filterByCategory(List<Produto> products, String category) {
        List<Produto> filteredList = new ArrayList<>();
        if (category == null || products == null) {
            return filteredList;
        }
        for (Produto product : products) {
            if (product.getCategoria() != null && product.getCategoria().equalsIgnoreCase(category)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    /**
     * Este método é chamado quando o usuário clica no botão "Voltar" da Toolbar.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Simplesmente executa a ação padrão de voltar.
        return true;
    }
}
