package com.example.meuapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app_database.db";
    private static final int DATABASE_VERSION = 8; 

    // ... (Definições de tabelas e colunas) ...
    private static final String TABLE_PRODUTOS = "produtos";
    private static final String COLUMN_P_ID = "id";
    private static final String COLUMN_P_NOME = "nome";
    private static final String COLUMN_P_DESCRICAO = "descricao";
    private static final String COLUMN_P_PRECO = "preco";
    private static final String COLUMN_P_CATEGORIA = "categoria";
    private static final String COLUMN_P_IMAGEM_ID = "imagem_id";

    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COLUMN_U_ID = "id";
    private static final String COLUMN_U_NOME = "nome";
    private static final String COLUMN_U_EMAIL = "email";
    private static final String COLUMN_U_SENHA = "senha";

    private static final String CREATE_TABLE_PRODUTOS = "CREATE TABLE " + TABLE_PRODUTOS + "(" + COLUMN_P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_P_NOME + " TEXT NOT NULL," + COLUMN_P_DESCRICAO + " TEXT," + COLUMN_P_PRECO + " TEXT NOT NULL," + COLUMN_P_CATEGORIA + " TEXT NOT NULL,"+ COLUMN_P_IMAGEM_ID + " INTEGER NOT NULL" + ");";
    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLE_USUARIOS + "(" + COLUMN_U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_U_NOME + " TEXT NOT NULL," + COLUMN_U_EMAIL + " TEXT NOT NULL UNIQUE," + COLUMN_U_SENHA + " TEXT NOT NULL" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUTOS);
        db.execSQL(CREATE_TABLE_USUARIOS);
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            addProduto(db, "Pão de Queijo", "", "R$ 5,50", "Salgados", R.drawable.paodequeijo);
            addProduto(db, "Salgado", "", "R$ 8,00", "Salgados", R.drawable.salgados);
            addProduto(db, "Folhados", "", "R$ 8,00", "Salgados", R.drawable.folhado);
            addProduto(db, "Lanche Natural", "", "R$ 9,00", "Salgados", R.drawable.lanchenatural);
            addProduto(db, "Frango Cremoso", "", "R$ 12,00", "Salgados", R.drawable.frangocremoso);
            addProduto(db, "Lombo e Muçarela", "", "R$ 12,00", "Salgados", R.drawable.lombocommussarela);
            addProduto(db, "Torta de Frango", "", "R$ 12,00", "Salgados", R.drawable.tortadefrango);
            addProduto(db, "Quiche", "", "R$ 12,00", "Salgados", R.drawable.quiche);
            addProduto(db, "Lanche no Pão Francês (Carne Louca)", "", "R$ 8,50", "Queridinhos", R.drawable.lanchenatural);
            addProduto(db, "Lanche no Pão Francês (Pernil)", "", "R$ 8,50", "Queridinhos", R.drawable.lanchepernil);
            addProduto(db, "Escondidinhos", "", "R$ 12,00", "Queridinhos", R.drawable.escondidinho);
            addProduto(db, "Caldos", "", "R$ 12,00", "Queridinhos", R.drawable.caldo);
            addProduto(db, "Marmitinhas", "Fidelidade: 10+1", "R$ 18,00", "Queridinhos", R.drawable.marmita);
            addProduto(db, "Salada de Frutas", "", "R$ 8,50", "Sobremesas", R.drawable.saladadefrutas);
            addProduto(db, "Bolo com Calda de Chocolate", "", "R$ 7,50", "Sobremesas", R.drawable.bolo);
            addProduto(db, "Donuts", "", "R$ 8,00", "Sobremesas", R.drawable.donuts);
            addProduto(db, "Tortas Doces", "", "R$ 12,00", "Sobremesas", R.drawable.tortadoce);
            addProduto(db, "Água", "", "R$ 3,50", "Bebidas Geladas", R.drawable.agua);
            addProduto(db, "Água com Gás", "", "R$ 4,50", "Bebidas Geladas", R.drawable.aguacomgas);
            addProduto(db, "Mini Refrigerante", "", "R$ 4,50", "Bebidas Geladas", R.drawable.minirefrigerante);
            addProduto(db, "Muppy", "", "R$ 5,00", "Bebidas Geladas", R.drawable.muppy);
            addProduto(db, "Toddynho", "", "R$ 5,50", "Bebidas Geladas", R.drawable.toddynho);
            addProduto(db, "Guaraviton", "", "R$ 6,00", "Bebidas Geladas", R.drawable.guaraviton);
            addProduto(db, "Suco Natural", "", "R$ 6,50", "Bebidas Geladas", R.drawable.suconatural);
            addProduto(db, "Refrigerantes", "", "R$ 7,50", "Bebidas Geladas", R.drawable.refrigerante);
            addProduto(db, "H2O", "", "R$ 8,00", "Bebidas Geladas", R.drawable.h20limao);
            addProduto(db, "Chá Gelado", "", "R$ 8,00", "Bebidas Geladas", R.drawable.chagelado);
            addProduto(db, "Red Bull", "(promoção)", "R$ 8,50", "Bebidas Geladas", R.drawable.redbull);
            addProduto(db, "Chá", "", "R$ 4,00", "Bebidas Quentes", R.drawable.cha);
            addProduto(db, "Café", "", "R$ 6,50", "Bebidas Quentes", R.drawable.cafe);
            addProduto(db, "Café com Leite", "", "R$ 8,00", "Bebidas Quentes", R.drawable.cafecomleite);
            addProduto(db, "Bebidas Nestlé", "", "R$ 10,00", "Bebidas Quentes", R.drawable.bebidanestle);
            addProduto(db, "Cappuccino", "", "R$ 12,00", "Bebidas Quentes", R.drawable.capuccino);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    // ... (Restante do código, que está correto)
    private void addProduto(SQLiteDatabase db, String nome, String descricao, String preco, String categoria, int imagemId) { ContentValues values = new ContentValues(); values.put(COLUMN_P_NOME, nome); values.put(COLUMN_P_DESCRICAO, descricao); values.put(COLUMN_P_PRECO, preco); values.put(COLUMN_P_CATEGORIA, categoria); values.put(COLUMN_P_IMAGEM_ID, imagemId); db.insert(TABLE_PRODUTOS, null, values); } public List<Produto> getAllProdutos() { List<Produto> produtos = new ArrayList<>(); SQLiteDatabase db = this.getReadableDatabase(); Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUTOS, null); int idCol = cursor.getColumnIndex(COLUMN_P_ID); int nomeCol = cursor.getColumnIndex(COLUMN_P_NOME); int descCol = cursor.getColumnIndex(COLUMN_P_DESCRICAO); int precoCol = cursor.getColumnIndex(COLUMN_P_PRECO); int catCol = cursor.getColumnIndex(COLUMN_P_CATEGORIA); int imgCol = cursor.getColumnIndex(COLUMN_P_IMAGEM_ID); if (cursor.moveToFirst()) { do { Produto produto = new Produto(); produto.setId(cursor.getInt(idCol)); produto.setNome(cursor.getString(nomeCol)); produto.setDescricao(cursor.getString(descCol)); produto.setPreco(cursor.getString(precoCol)); produto.setCategoria(cursor.getString(catCol)); produto.setImagemId(cursor.getInt(imgCol)); produtos.add(produto); } while (cursor.moveToNext()); } cursor.close(); return produtos; } public boolean addUser(Usuario usuario) { SQLiteDatabase db = this.getWritableDatabase(); ContentValues values = new ContentValues(); values.put(COLUMN_U_NOME, usuario.getNome()); values.put(COLUMN_U_EMAIL, usuario.getEmail()); values.put(COLUMN_U_SENHA, usuario.getSenha()); long result = db.insert(TABLE_USUARIOS, null, values); return result != -1; } public boolean checkUser(String email, String password) { SQLiteDatabase db = this.getReadableDatabase(); String[] columns = {COLUMN_U_ID}; String selection = COLUMN_U_EMAIL + " = ?" + " AND " + COLUMN_U_SENHA + " = ?"; String[] selectionArgs = {email, password}; Cursor cursor = db.query(TABLE_USUARIOS, columns, selection, selectionArgs, null, null, null); int count = cursor.getCount(); cursor.close(); return count > 0; } public boolean checkUserExists(String email) { SQLiteDatabase db = this.getReadableDatabase(); String[] columns = {COLUMN_U_ID}; String selection = COLUMN_U_EMAIL + " = ?"; String[] selectionArgs = {email}; Cursor cursor = db.query(TABLE_USUARIOS, columns, selection, selectionArgs, null, null, null); int count = cursor.getCount(); cursor.close(); return count > 0; } public Usuario getUserByEmail(String email) { SQLiteDatabase db = this.getReadableDatabase(); Cursor cursor = db.query(TABLE_USUARIOS, new String[]{COLUMN_U_ID, COLUMN_U_NOME, COLUMN_U_EMAIL, COLUMN_U_SENHA}, COLUMN_U_EMAIL + " = ?", new String[]{email}, null, null, null, "1"); Usuario user = null; if (cursor != null && cursor.moveToFirst()) { user = new Usuario(); user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_U_ID))); user.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_U_NOME))); user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_U_EMAIL))); cursor.close(); } return user; }
}
