package br.com.project.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin;
    TextView user, password;
    boolean isValid, isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.btnLogin);
        user = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtSenha);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkUserAndPassoword()) {
                    if (validateLogin(user, password)) {
                        Intent cat;
                        if (isAdmin){
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
                }
            }
        });
    }

    private boolean validateLogin(TextView user, TextView password) {
        getValueToConvert(user.getText().toString(), password.getText().toString());
        if (isValid){
            return true;
        }
        return false;
    }

    private boolean checkUserAndPassoword() {
        boolean check = false;
        if (user.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
            check = true;
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Senha não pode ser vazia!", Toast.LENGTH_SHORT).show();
            check = true;
        }
        return check;
    }

    private void getValueToConvert(String user, String password) {

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(createRoute(user, password), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String data = new String(response);
                try {
                    loadData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String createRoute(String user, String password) {
        String url = "https://app-e-commerce.herokuapp.com/user/validate/".concat(user).concat("/").concat(password);
        return url;
    }


    private void loadData(String data) throws JSONException {
       if (data.contains("\"valid\":true")){
           this.isValid = true;
       }
       if (data.contains("\"admin\":true")){
           this.isAdmin = true;
       }
    }

}