package com.example.meuapp;

import java.util.ArrayList;
import java.util.List;

/**
 * PROJETO: MeuApp (Comedoria da Tia)
 * ALUNO: [Seu Nome Aqui]
 *
 * DESCRIÇÃO:
 * Esta é uma classe Singleton, o que significa que só existe UMA instância dela em todo o aplicativo.
 * Eu a criei para ser o "cérebro" do carrinho de compras.
 *
 * FUNCIONALIDADES:
 * 1. Mantém uma lista única e centralizada dos itens que o usuário adicionou ao carrinho.
 * 2. Fornece métodos para adicionar, remover e buscar os itens do carrinho de qualquer tela do app.
 * 3. Lida com a lógica de incrementar a quantidade se um produto que já está no carrinho for adicionado novamente.
 */
public class CarrinhoManager {

    // A única instância da classe.
    private static CarrinhoManager instance;
    // A lista de itens do carrinho.
    private List<CarrinhoItem> carrinhoItems = new ArrayList<>();

    // O construtor é privado para garantir que ninguém possa criar uma nova instância de fora.
    private CarrinhoManager() {
    }

    /**
     * O método público para obter a única instância da classe.
     * Se a instância ainda não existe, ela é criada aqui.
     */
    public static synchronized CarrinhoManager getInstance() {
        if (instance == null) {
            instance = new CarrinhoManager();
        }
        return instance;
    }

    /**
     * Adiciona um item ao carrinho.
     * Antes de adicionar, ele verifica se um item igual já existe.
     * Se existir, ele apenas aumenta a quantidade. Senão, adiciona o novo item à lista.
     */
    public void addItem(CarrinhoItem newItem) {
        for (CarrinhoItem item : carrinhoItems) {
            if (item.getNome().equals(newItem.getNome())) {
                item.setQuantidade(item.getQuantidade() + 1);
                return; // Para a execução do método aqui.
            }
        }
        // Se o loop terminar sem encontrar um item igual, o novo item é adicionado.
        carrinhoItems.add(newItem);
    }

    /**
     * Remove um item da lista do carrinho.
     */
    public void removeItem(CarrinhoItem item) {
        carrinhoItems.remove(item);
    }

    /**
     * Retorna a lista completa de itens no carrinho.
     */
    public List<CarrinhoItem> getItems() {
        return carrinhoItems;
    }

    /**
     * Limpa completamente o carrinho, removendo todos os itens.
     */
    public void clearCart() {
        carrinhoItems.clear();
    }
}
