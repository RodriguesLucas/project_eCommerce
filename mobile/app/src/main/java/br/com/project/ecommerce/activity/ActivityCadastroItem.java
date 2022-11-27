package br.com.project.ecommerce.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import br.com.project.ecommerce.ProductAdapter;
import br.com.project.ecommerce.R;
import br.com.project.ecommerce.dtos.ProductDTO;
import br.com.project.ecommerce.dtos.ProductReturnDTO;
import br.com.project.ecommerce.dtos.PurchaseDTO;
import cz.msebera.android.httpclient.Header;

public class ActivityCadastroItem extends AppCompatActivity {
    RecyclerView recyclerId;
    ProductAdapter adapter;
    Button btnCadastroItem;
    TextView textTv, txtPlatCad, txtAnoCad, txtNome, txtValor2;
    List<ProductDTO> productDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        recyclerId = findViewById(R.id.recyclerId);
        textTv = findViewById(R.id.txtTV);

        txtPlatCad = findViewById(R.id.txtPlatCad);
        txtAnoCad = findViewById(R.id.txtAnoCad);
        txtNome = findViewById(R.id.txtNome);
        txtValor2 = findViewById(R.id.txtValor2);

        setTotalSales();
        getList();

        btnCadastroItem = findViewById(R.id.btnCadastroItem);
        btnCadastroItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkItens()) {
                    String url = "https://app-e-commerce.herokuapp.com/product/create/product";
                    RequestParams requestParams = new RequestParams();
                    requestParams.add("name", txtNome.getText().toString());
                    requestParams.add("launchYear", txtAnoCad.getText().toString());
                    requestParams.add("stock", "10");
                    requestParams.add("price", txtValor2.getText().toString());
                    requestParams.add("platforms", txtPlatCad.getText().toString());
                    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                    asyncHttpClient.post(url, requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                String data = new String(responseBody, "UTF-8");
                                ProductReturnDTO userReturnDTO = new Gson().fromJson(data, ProductReturnDTO.class);
                                if (userReturnDTO.isSucess()){
                                    Toast.makeText(getApplicationContext(), "Producto criado com sucesso!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error! Já existe um produto com esse nome.", Toast.LENGTH_SHORT).show();
                                }
                                Log.d("TAG", "onSuccessValue: ".concat(data));

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }
            }
        });
    }

    private boolean checkItens() {
        boolean check = false;
        if (txtNome.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nome não pode ser vazio!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (txtAnoCad.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ano de lançamento não pode ser vazio!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (txtValor2.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Valor não pode ser vazio!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (txtPlatCad.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Plataforma não pode ser vazio!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return check;
    }

    private void setTotalSales() {
        String url = "https://app-e-commerce.herokuapp.com/sales/sum";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String data = new String(responseBody, "UTF-8");
                    PurchaseDTO userReturnDTO = new Gson().fromJson(data, PurchaseDTO.class);
                    textTv.setText("Total das vendas".concat("\n").concat(String.format("R$: %.2f", userReturnDTO.getSum())));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getList() {
        productDTOList = new ArrayList<ProductDTO>();

        String url = createRoute();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String data = new String(responseBody, "UTF-8");
                    ProductDTO[] productist = new Gson().fromJson(data, ProductDTO[].class);

                    for (int i = 0; i < productist.length; i++) {
                        productDTOList.add(new ProductDTO(productist[i].getName(), productist[i].getStock(), productist[i].getPrice()));
                        recyclerId.setHasFixedSize(true);
                        recyclerId.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new ProductAdapter(getApplicationContext(), productDTOList);
                        recyclerId.setAdapter(adapter);
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


    private String createRoute() {
        return "https://app-e-commerce.herokuapp.com/product/findAll";
    }
}