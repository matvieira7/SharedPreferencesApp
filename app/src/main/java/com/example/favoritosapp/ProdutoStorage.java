package com.example.favoritosapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdutoStorage {

    private static final String PREF_NAME = "FavoritosApp";
    private static final String KEY_PRODUTOS = "produtos";

    private final SharedPreferences preferences;

    public ProdutoStorage(Context context) {
        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
        );
    }

    public void adicionarProduto(String produto) {

        String dados = preferences.getString(KEY_PRODUTOS, "");

        if (dados.isEmpty()) {
            dados = produto;
        } else {
            dados = dados + "|" + produto;
        }

        preferences.edit()
                .putString(KEY_PRODUTOS, dados)
                .apply();
    }

    public List<String> consultarProdutos() {

        String dados = preferences.getString(KEY_PRODUTOS, "");

        if (dados.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(
                Arrays.asList(dados.split("\\|"))
        );
    }
}