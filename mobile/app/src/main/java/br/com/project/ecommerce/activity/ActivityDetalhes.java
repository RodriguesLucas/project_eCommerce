package br.com.project.ecommerce.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.project.ecommerce.R;

public class ActivityDetalhes extends AppCompatActivity {
    TextView txtNomeJogo, txtAno, txtValor1, txtPlataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNomeJogo = findViewById(R.id.txtNomeJogo);
        txtAno = findViewById(R.id.txtAno);
        txtValor1 = findViewById(R.id.txtValor1);
        txtPlataforma = findViewById(R.id.txtPlataforma);



        Log.d("TAG", "onCreate:aaaaaaaaaaaaaaaa " + getDataAndSafeSpinner());
    }


    private String getDataAndSafeSpinner() {
        String value;
        SharedPreferences sharedPreferences = getSharedPreferences("chaveGeral", Context.MODE_PRIVATE);
        value = sharedPreferences.getString("value", "");
        return value;
    }

}