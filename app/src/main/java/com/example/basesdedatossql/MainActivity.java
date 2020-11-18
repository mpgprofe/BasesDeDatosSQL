package com.example.basesdedatossql;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextID, editTextModelo, editTextMarca, editTextPrecio;
    Button buttonGuardar, buttonBorrar, buttonMostrar, buttonActualizar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextID = findViewById(R.id.editTextId);
        editTextModelo = findViewById(R.id.editTextModelo);
        editTextMarca = findViewById(R.id.editTextMarca);
        editTextPrecio = findViewById(R.id.editTextPrecio);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonBorrar = findViewById(R.id.buttonBorrar);
        buttonMostrar = findViewById(R.id.buttonMostrar);
        buttonActualizar = findViewById(R.id.buttonActualizar);
        listView = findViewById(R.id.lista);

        ManejadorBD manejadorBD = new ManejadorBD(this);


        buttonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = manejadorBD.listar();
                ArrayAdapter<String> adapter;
                List<String> lista = new ArrayList<>();

                if ((cursor != null) && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String fila = "";
                        fila += "ID: " + cursor.getString(0);
                        fila += " MODELO: " + cursor.getString(1);
                        fila += " MARCA: " + cursor.getString(2);
                        fila += " PRECIO: " + cursor.getString(3);
                        lista.add(fila);
                    }
                    adapter = new ArrayAdapter<>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item, lista);
                    listView.setAdapter(adapter);

                } else {
                    Toast.makeText(MainActivity.this, "Vacío", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean resultado = manejadorBD.insertar(editTextModelo.getText().toString(),
                        editTextMarca.getText().toString(), editTextPrecio.getText().toString());

                if (resultado) {
                    Toast.makeText(MainActivity.this, "Insertado portátil OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error en la inserción.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean borrado = manejadorBD.borrar(editTextID.getText().toString());
                if (borrado) {
                    Toast.makeText(MainActivity.this, "Borrado Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Nada a borrar.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean resultado = manejadorBD.actualizar(editTextID.getText().toString(),
                        editTextModelo.getText().toString(),
                        editTextMarca.getText().toString(), editTextPrecio.getText().toString());

                if (resultado) {
                    Toast.makeText(MainActivity.this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error en la actualización.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}