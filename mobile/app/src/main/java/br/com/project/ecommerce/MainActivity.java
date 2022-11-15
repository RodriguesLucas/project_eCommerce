package br.com.project.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                if(!checkUserAndPassoword()){
                    if(validateLogin(user, password)){
                        // Chama outra tela
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateLogin(TextView user, TextView password) {
        getValueToConvert(user.getText().toString(), password.getText().toString());
        return false;
    }

    private boolean checkUserAndPassoword() {
        boolean check = false;
        if (user.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
            check = true;
        } else if (password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Senha não pode ser vazia!", Toast.LENGTH_SHORT).show();
            check = true;
        }
        return check;
    }

    private void getValueToConvert(String user, String password) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("user", user);
        params.add("password", password);

        client.get("http://localhost:80/user/validate", new AsyncHttpResponseHandler() {
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


    private void loadData(String data) throws JSONException {
        JSONObject resp = new JSONObject(data);
        isValid = Boolean.getBoolean(resp.get("isValid").toString());
        isAdmin = Boolean.getBoolean(resp.get("isAdmin").toString());

        UserReturnDTO userReturnDTO = new UserReturnDTO();
        userReturnDTO.setAdmin(isAdmin);
        userReturnDTO.setValid(isValid);
    }

}