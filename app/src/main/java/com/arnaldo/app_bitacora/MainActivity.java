package com.arnaldo.app_bitacora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText ml_bitacora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ml_bitacora = (EditText) findViewById(R.id.ml_bitacora);
        String archivos[] = fileList();

        if (ArchivoExiste(archivos, "bitacora.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String bitacoracompleta = "";

                while (linea != null) {
                    bitacoracompleta = bitacoracompleta + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                ml_bitacora.setText(bitacoracompleta);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al intentar abrir archivo" + e);
            }
    }

    private Boolean ArchivoExiste(String archivos[], String nombrearchivo) {
        for (int i = 0; i < archivos.length; i++) {
            if (nombrearchivo.equals(archivos[i])) {
                return true;
            }
        }
        return false;
    }

    public void Guardar(View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(ml_bitacora.getText().toString());
            archivo.flush(); //Limpia el archivo
            archivo.close();
            Toast.makeText(this, "Bitacora guardada correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer archivo" + e);
        }
    }
}


