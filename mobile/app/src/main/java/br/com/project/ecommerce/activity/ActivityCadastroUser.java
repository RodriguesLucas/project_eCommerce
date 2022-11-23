package br.com.project.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import br.com.project.ecommerce.R;
import br.com.project.ecommerce.dtos.UserReturnDTO;
import cz.msebera.android.httpclient.Header;

public class ActivityCadastroUser extends AppCompatActivity {
    TextView txtUsuario, txtsenha, txtSenha2;
    Button btnCadastro;
    boolean isValid, isAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        isValid = false;
        isAdmin = false;

        txtUsuario = findViewById(R.id.txtUsuario);
        txtsenha = findViewById(R.id.txtSenha);
        txtSenha2 = findViewById(R.id.txtSenha2);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkUserAndPassword(txtUsuario, txtsenha, txtSenha2)) {
                    cadastreUser(txtUsuario, txtsenha);
                }
            }
        });
    }

    private void cadastreUser(TextView user, TextView password) {

        String url = createRoute();
        RequestParams requestParams = new RequestParams();
        requestParams.add("name", user.getText().toString());
        requestParams.add("password", password.getText().toString());
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String data = new String(responseBody, "UTF-8");
                    UserReturnDTO userReturnDTO = new Gson().fromJson(data, UserReturnDTO.class);
                    Log.d("TAG", "onSuccess: ".concat(data));
                    Log.d("TAG", "onSuccess: ".concat(String.valueOf(userReturnDTO.isValid())));
                    if (userReturnDTO.isValid()){
                        Toast.makeText(getApplicationContext(), "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                        Thread.sleep(100);
                        Intent cat = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(cat);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário indisponível!", Toast.LENGTH_LONG).show();
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
        return "https://app-e-commerce.herokuapp.com/user/create";
    }

    private boolean checkUserAndPassword(TextView txtUsuario, TextView txtsenha, TextView txtSenha2) {
        boolean check = false;
        if (txtUsuario.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
            check = true;
        } else if (txtsenha.getText().toString().isEmpty() || txtSenha2.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Senha não pode ser vazia!", Toast.LENGTH_SHORT).show();
            check = true;
        } else if (!txtsenha.getText().toString().equals(txtSenha2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Senhas não são iguais!", Toast.LENGTH_SHORT).show();
            check = true;
        }
        return check;
    }

}