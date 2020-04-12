package avc.com.avanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.senha);
        e3 = (EditText)findViewById(R.id.confirmarSenha);
        b1 = (Button)findViewById(R.id.botaoRegistro);
        b2 = (Button)findViewById(R.id.botaoLogin);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if ( s1.equals("") || s2.equals("") || s3.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (s2.equals(s3)) {
                        Boolean checkEmail = db.checkEmail(s1);
                        if (checkEmail==true) {
                            Boolean insert = db.insert(s1,s2);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(), "Registrado com Sucesso", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "E-mail já existente, inserir outro", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"As senhas não combinam", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

}


/*
backup MANIFESTS

    <activity android:name=".LoginActivity"></activity>

    <activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    */