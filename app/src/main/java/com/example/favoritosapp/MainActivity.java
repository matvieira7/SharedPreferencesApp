package com.example.favoritosapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtProduto;
    private Button btnAdicionar;
    private Button btnConsultar;
    private TextView txtResultado;

    private ProdutoStorage produtoStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtProduto = findViewById(R.id.edtProduto);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnConsultar = findViewById(R.id.btnConsultar);
        txtResultado = findViewById(R.id.txtResultado);

        produtoStorage = new ProdutoStorage(this);

        btnAdicionar.setOnClickListener(v -> {

            String produto = edtProduto
                    .getText()
                    .toString()
                    .trim();

            if (produto.isEmpty()) {

                Toast.makeText(
                        this,
                        "Digite um produto.",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            produtoStorage.adicionarProduto(produto);

            edtProduto.setText("");

            Toast.makeText(
                    this,
                    "Produto salvo!",
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnConsultar.setOnClickListener(v -> {

            List<String> produtos =
                    produtoStorage.consultarProdutos();

            if (produtos.isEmpty()) {

                txtResultado.setText(
                        "Nenhum produto salvo."
                );

                return;
            }

            StringBuilder resultado =
                    new StringBuilder();

            for (int i = 0; i < produtos.size(); i++) {

                resultado
                        .append(i + 1)
                        .append(" - ")
                        .append(produtos.get(i))
                        .append("\n");
            }

            txtResultado.setText(
                    resultado.toString()
            );
        });
    }
}