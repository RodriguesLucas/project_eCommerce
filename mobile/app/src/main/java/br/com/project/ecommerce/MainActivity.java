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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin, buttonCadastrar;
    TextView user, password;
    boolean isValid, isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isValid = false;
        isAdmin = false;
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
                    if (validateLogin(user, password)) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent cat;
                        Log.d("TAG", "isValid: "+ isValid);
                        Log.d("TAG", "isAdmin: "+ isAdmin);
                        if (isAdmin) {
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
        if (isValid) {
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

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Create URL
                URL githubEndpoint = null;
                try {
                    githubEndpoint = new URL(createRoute(user, password));
                    // Create connection
                    HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();

                    InputStream responseBody = myConnection.getInputStream();

                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");

                    JsonReader jsonReader = new JsonReader(responseBodyReader);

                    jsonReader.beginObject(); // Start processing the JSON object
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String key = jsonReader.nextName(); // Fetch the next key
                        if (key.equals("admin")) { // Check if desired key
                            boolean value = jsonReader.nextBoolean();
                            isAdmin = value;

                        } else if (key.equals("valid")) { // Check if desired key
                            boolean value = jsonReader.nextBoolean();
                            isValid = value;

                        } else {
                            jsonReader.skipValue(); // Skip values of other keys
                        }
                    }

                    jsonReader.close();
                    myConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private String createRoute(String user, String password) {
        String url = "https://app-e-commerce.herokuapp.com/user/validate/".concat(user).concat("/").concat(password);
        return url;
    }

}