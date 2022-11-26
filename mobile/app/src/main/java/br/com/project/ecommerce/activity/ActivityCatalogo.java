package br.com.project.ecommerce.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import br.com.project.ecommerce.ProductAdapter;
import br.com.project.ecommerce.R;
import br.com.project.ecommerce.dtos.ProductDTO;
import cz.msebera.android.httpclient.Header;

public class ActivityCatalogo extends AppCompatActivity{
    RecyclerView recyclerId;
    ProductAdapter adapter;
    List<ProductDTO> productDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        recyclerId = findViewById(R.id.recyclerId);
        getList();

    }

    private void getList(){
        productDTOList = new ArrayList<ProductDTO>();

        String url = createRoute();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String  data = new String(responseBody, "UTF-8");
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