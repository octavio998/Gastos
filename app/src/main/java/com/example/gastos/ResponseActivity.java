package com.example.gastos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResponseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        Double promedioProductos = getIntent().getDoubleExtra("promedioProductos", 0.0);
        Double totalSinIVA = getIntent().getDoubleExtra("totalSinIVA", 0.0);
        Double soloIVA = getIntent().getDoubleExtra("soloIVA", 0.0);
        Integer cantidadProductos = getIntent().getIntExtra("cantidadProductos", 0);
        Double totalCompra = getIntent().getDoubleExtra("totalCompra", 0.0);

        TextView cantidadTextView = findViewById(R.id.cantidadProductosMonto);
        cantidadTextView.setText(String.valueOf(cantidadProductos));

        TextView totalSinIVATextView = findViewById(R.id.totalSinIVAMonto);
        totalSinIVATextView.setText(String.valueOf(totalSinIVA));

        TextView soloIVAMontoTextView = findViewById(R.id.soloIVAMonto);
        soloIVAMontoTextView.setText(String.valueOf(soloIVA));

        TextView promedioProductosMontoTextView = findViewById(R.id.promedioProductosMonto);
        promedioProductosMontoTextView.setText(String.valueOf(promedioProductos));

        TextView totalCompraTextView = findViewById(R.id.totalCompraMonto);
        totalCompraTextView.setText(String.valueOf(totalCompra));





    }

}
