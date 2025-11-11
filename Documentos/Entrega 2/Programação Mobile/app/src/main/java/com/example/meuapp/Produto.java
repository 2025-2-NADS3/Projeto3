package com.example.meuapp;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private String preco;
    private String categoria;
    private int imagemId; // Novo campo

    public Produto() {
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getPreco() { return preco; }
    public void setPreco(String preco) { this.preco = preco; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getImagemId() { return imagemId; } // Novo getter
    public void setImagemId(int imagemId) { this.imagemId = imagemId; } // Novo setter
}
