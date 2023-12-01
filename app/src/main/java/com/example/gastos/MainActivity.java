package com.example.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextMonto;
    private Button btnSubir;
    private LinearLayout layoutPadre;
    private int contador = 1;
    private Button btnRedirect;
    //private ArrayList<EditText> listaMontos;
    //variables de resultado
    private Double promedioProductos;
    private Double totalSinIVA;
    private Double soloIVA;
    private Integer cantidadProductos;
    private Double totalCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextMonto = findViewById(R.id.editTextMonto);
        btnSubir = findViewById(R.id.btnSubir);
        btnRedirect = findViewById(R.id.btnRedirect);

        btnSubir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String valorEditTextMonto = editTextMonto.getText().toString();

                String valorEditText = editTextNombre.getText().toString();
                if (valorEditText.isEmpty() || valorEditTextMonto.isEmpty()) {
                    // Mostrar un Toast con un mensaje de error
                    Toast.makeText(MainActivity.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    int montoNumerico = Integer.parseInt(valorEditTextMonto);
                    agregarRegistro(valorEditText, montoNumerico);
                }



            }
        });
        btnRedirect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResponseActivity.class);

                // Iniciar la nueva actividad

                ArrayList<Double> myList = obtenerMontos(layoutPadre);

                String arrayListAsString = arrayListToString(myList);
                //prueba de array stringifeado, lo veo bien
                // System.out.println(arrayListAsString);
                intent.putExtra("cantidadProductos", getCantidadProductos());
                intent.putExtra("promedioProductos", getPromedioProductos());
                intent.putExtra("soloIVA", getSoloIVA());
                intent.putExtra("totalSinIVA", getTotalSinIVA());
                intent.putExtra("totalCompra", getTotalCompra());
                startActivity(intent);
            }
        });
    }



    private ArrayList<Double> obtenerMontos(LinearLayout layoutPadre) {
        ArrayList<Double> montos = new ArrayList<>();
        Double contador = 0.0;
        // Iterar sobre los hijos del LinearLayout padre
        for (int i = 0; i < layoutPadre.getChildCount(); i++) {
            // Obtener el LinearLayout hijo actual
            LinearLayout layoutHijo = (LinearLayout) layoutPadre.getChildAt(i);

            // Obtener el segundo TextView del LinearLayout hijo
            TextView segundoTextView = (TextView) layoutHijo.getChildAt(1);

            // Obtener la cadena del TextView y eliminar el símbolo de dólar
            String montoString = segundoTextView.getText().toString().replace("Monto: $", "");

            // Convertir la cadena a un valor numérico y agregarlo al ArrayList
            double monto = Double.parseDouble(montoString);
            montos.add(monto);
            Log.d("ValorMontos", "Monto #" + (i + 1) + ": " + monto);
            contador += monto;
        }

        this.setTotalCompra(contador);
        this.setSoloIVA(contador * 0.21);
        this.setTotalSinIVA(contador-this.getSoloIVA());
        this.setCantidadProductos(montos.size());
        this.setPromedioProductos(contador / this.getCantidadProductos());
        Log.d("total compra:", String.valueOf(getTotalCompra()));
        Log.d("solo iva:", String.valueOf(getSoloIVA()));
        Log.d("total sin iva:", String.valueOf(getTotalSinIVA()));
        Log.d("cantidad de productos: ", String.valueOf(getCantidadProductos()));
        Log.d("promedio de productos: ", String.valueOf(getPromedioProductos()));
        // Devolver el ArrayList de montos
        return montos;
    }


    public static <T> String arrayListToString(ArrayList<T> arrayList) {
        return arrayList.toString();
    }
    private void agregarRegistro(String nombre, int monto){
        layoutPadre = findViewById(R.id.layoutPadre);
        LinearLayout layoutHijo = new LinearLayout(this);
        layoutHijo.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layoutHijo.setOrientation(LinearLayout.VERTICAL);

        TextView textViewNombre = new TextView(this);
        textViewNombre.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textViewNombre.setText("Nombre: " + nombre);
        textViewNombre.setId(View.generateViewId()); // Generar ID único
        layoutHijo.addView(textViewNombre);

        TextView textViewMonto = new TextView(this);
        textViewMonto.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textViewMonto.setText("Monto: $" + monto);
        textViewMonto.setId(View.generateViewId()); // Generar ID único
        layoutHijo.addView(textViewMonto);

        layoutHijo.setId(View.generateViewId());
        layoutPadre.addView(layoutHijo);

        editTextNombre.setText("");
        editTextMonto.setText("");
        // Mostrar el valor en el Log
        Log.i("MainActivity", "Valor del EditText: " + nombre + monto);

    }
    public Double getTotalCompra() {
        return totalCompra;
    }
    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Double getPromedioProductos() {
        return promedioProductos;
    }

    public void setPromedioProductos(Double promedioProductos) {
        this.promedioProductos = promedioProductos;
    }

    public Double getTotalSinIVA() {
        return totalSinIVA;
    }

    public void setTotalSinIVA(Double totalSinIVA) {
        this.totalSinIVA = totalSinIVA;
    }

    public Double getSoloIVA() {
        return soloIVA;
    }

    public void setSoloIVA(Double soloIVA) {
        this.soloIVA = soloIVA;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
}