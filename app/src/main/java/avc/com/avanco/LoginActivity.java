package avc.com.avanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.login_email);
        e2 = (EditText) findViewById(R.id.login_senha);
        b1 = (Button) findViewById(R.id.botaoEntrar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String senha = e2.getText().toString();
                Boolean checarEmailSenha = db.emailSenha(email, senha);
                if (checarEmailSenha == true) {

                    Toast.makeText(getApplicationContext(), "Sucesso ao logar", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                 else
                    Toast.makeText(getApplicationContext(), "Email ou senha incorreto", Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
