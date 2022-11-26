package br.com.project.ecommerce.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import br.com.project.ecommerce.R;
import br.com.project.ecommerce.dtos.ProductDTO;
import br.com.project.ecommerce.dtos.PurchaseDTO;
import cz.msebera.android.httpclient.Header;

public class ActivityDetalhes extends AppCompatActivity {

    TextView txtNomeJogo, txtAno, txtValor1, txtPlataforma;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNomeJogo = findViewById(R.id.txtNomeJogo);
        txtAno = findViewById(R.id.txtAno);
        txtValor1 = findViewById(R.id.txtValor1);
        txtPlataforma = findViewById(R.id.txtPlataforma);
        button = findViewById(R.id.btnFinalizar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy();
            }
        });

        getProduct();

    }

    private void buy() {

        String url = createRoute();
        RequestParams requestParams = new RequestParams();
        requestParams.add("productName", txtNomeJogo.getText().toString());
        requestParams.add("userName", getDataAndSafeSpinnerName());

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String data = new String(responseBody, "UTF-8");
                    PurchaseDTO userReturnDTO = new Gson().fromJson(data, PurchaseDTO.class);

                    if (!userReturnDTO.isSucess()){
                        Toast.makeText(getApplicationContext(), "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        Thread.sleep(100);
                        Intent cat = new Intent(getApplicationContext(), ActivityCatalogo.class);
                        startActivity(cat);

                    } else {
                        Toast.makeText(getApplicationContext(), "Falha ao realizar a compra, tente novamente mais tarde!", Toast.LENGTH_SHORT).show();
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private String createRoute() {
        return "https://app-e-commerce.herokuapp.com/sales/purchase";
    }

    private void getProduct(){
        String url = createRoute(getDataAndSafeSpinner());

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String  data = new String(responseBody, "UTF-8");
                    ProductDTO product = new Gson().fromJson(data, ProductDTO.class);
                    txtNomeJogo.setText(product.getName());
                    txtAno.setText(product.getLaunchYear());
                    txtPlataforma.setText(product.getPlatforms());
                    txtValor1.setText(product.getPrice().toString());

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private String createRoute(String name) {
        return "https://app-e-commerce.herokuapp.com/product/findByName/".concat(name);
    }


    private String getDataAndSafeSpinner() {
        String value;
        SharedPreferences sharedPreferences = getSharedPreferences("chaveGeral", Context.MODE_PRIVATE);
        value = sharedPreferences.getString("value", "");
        return value;
    }

    private String getDataAndSafeSpinnerName() {
        String value;
        SharedPreferences sharedPreferences = getSharedPreferences("chaveGeral", Context.MODE_PRIVATE);
        value = sharedPreferences.getString("valueName", "");
        return value;
    }

}