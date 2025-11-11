package com.example.meuapp;

public class CarrinhoItem {
    private String nome;
    private String preco;
    private int imagem;
    private int quantidade;

    public CarrinhoItem(String nome, String preco, int imagem) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
        this.quantidade = 1;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }

    public int getImagem() {
        return imagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
