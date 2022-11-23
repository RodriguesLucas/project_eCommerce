package br.com.project.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin, buttonCadastrar;
    TextView user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin = findViewById(R.id.btnLogin);
        buttonCadastrar = findViewById(R.id.btnCadastrar);
        user = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtSenha);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cat = new Intent(getApplicationContext(), ActivityCadastroUser.class);
                startActivity(cat);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkUserAndPassoword()) {
                    getValueToConvert(user.getText().toString(), password.getText().toString());

                }
            }
        });
    }

    private boolean checkUserAndPassoword() {
        boolean check = false;
        if (user.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuário não pode estar vazio!", Toast.LENGTH_SHORT).show();
            check = true;

        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Senha não pode estar vazia!", Toast.LENGTH_SHORT).show();
            check = true;
        }

        return check;
    }

    private void getValueToConvert(String user, String password) {
        String url = createRoute(user, password);
        RequestParams requestParams = new RequestParams();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String data = new String(responseBody, "UTF-8");
                    UserReturnDTO userReturnDTO = new Gson().fromJson(data, UserReturnDTO.class);
                    Thread.sleep(100);
                    Intent cat;

                    Log.d("TAG", "isValid: " + userReturnDTO.isValid());
                    Log.d("TAG", "isAdmin: " + userReturnDTO.isAdmin());
                    if (userReturnDTO.isValid()) {
                        if (userReturnDTO.isAdmin()) {
                            Log.d("TAG", "ActivityCadastroItem");
                            cat = new Intent(getApplicationContext(), ActivityCadastroItem.class);
                        } else {
                            Log.d("TAG", "ActivityCatalogo");
                            cat = new Intent(getApplicationContext(), ActivityCatalogo.class);
                        }
                        startActivity(cat);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
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

    private String createRoute(String user, String password) {
        String url = "https://app-e-commerce.herokuapp.com/user/validate/".concat(user).concat("/").concat(password);
        return url;
    }
}