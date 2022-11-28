package br.com.project.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import br.com.project.ecommerce.R;
import br.com.project.ecommerce.dtos.UserReturnDTO;
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
                            cat = new Intent(getApplicationContext(), ActivityCadastroItem.class);

                        } else {
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
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("chaveGeral", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("valueName", user);
        editor.commit();

        String url = "https://app-e-commerce.herokuapp.com/user/validate/".concat(user).concat("/").concat(password);
        return url;
    }
}