package com.example.calcularVenda

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnCalcular: Button = findViewById(R.id.btCalcular)
        var btnSalvar: Button = findViewById(R.id.btSalvar)
        var btnRecuperar: Button = findViewById(R.id.btRecuperar)

        var pCusto: EditText = findViewById(R.id.txtPrecoCusto)
        var pVenda: EditText = findViewById(R.id.txtPrecoVenda)
        var produto: EditText = findViewById(R.id.txtNomeProduto)

        val sh = getSharedPreferences("calculadora", Context.MODE_PRIVATE)

        btnSalvar.setOnClickListener { v ->
            var nomeProdutoTexto = produto.text.toString()
            var valorPrecoCusto = pCusto.text.toString()
            var valorPrecoVenda = pVenda.text.toString()

            sh.edit().putString(nomeProdutoTexto, valorPrecoCusto + ";" + valorPrecoVenda).apply()
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()

            produto.setText("")
            pCusto.setText("")
            pVenda.setText("")
        }



        btnCalcular.setOnClickListener { v ->
            var valorPCusto = pCusto.text.toString().toFloat()
            var valorPVenda = pVenda.text.toString().toFloat()

            if (valorPCusto < valorPVenda) {
                Toast.makeText(
                    this,
                    "Lucro: " + (valorPVenda - valorPCusto),
                    Toast.LENGTH_LONG
                ).show()
            } else if (valorPCusto == valorPVenda) {
                Toast.makeText(
                    this,
                    "Sem lucro e prejuizo ",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Prejuizo: " + (valorPCusto - valorPVenda),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}