package br.com.project.ecommerce;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ActivityCadastroUser extends AppCompatActivity {
    TextView txtUsuario, txtsenha, txtSenha2;
    Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtsenha = findViewById(R.id.txtSenha);
        txtSenha2 = findViewById(R.id.txtSenha2);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkUserAndPassword(txtUsuario, txtsenha, txtSenha2)){

                }
            }
        });



    }

    private void cadastreUser(String user, String password) {

      /*  AsyncTask.execute(new Runnable() {
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
        });*/

    }

    private boolean checkUserAndPassword(TextView txtUsuario, TextView txtsenha, TextView txtSenha2) {
        boolean check = false;
        if (txtUsuario.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
            check = true;
        } else if (txtsenha.getText().toString().isEmpty() || txtSenha2.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Senha não pode ser vazia!", Toast.LENGTH_SHORT).show();
            check = true;
        } else if (!txtsenha.getText().toString().equals(txtSenha2.getText().toString())){
            Toast.makeText(getApplicationContext(), "Senhas não são iguais!", Toast.LENGTH_SHORT).show();
            check = true;
        }
        return check;
    }


}