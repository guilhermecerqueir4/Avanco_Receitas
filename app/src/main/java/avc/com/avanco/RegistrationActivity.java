package avc.com.avanco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseHelper db;
     EditText e1,e2,e3, e4 ;
    Button b1, b2;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.nomeUsuario);
        e2 = (EditText)findViewById(R.id.senha);
        e3 = (EditText)findViewById(R.id.confirmarSenha);
        e4 = (EditText)findViewById(R.id.phone);
        b1 = (Button)findViewById(R.id.botaoRegistro);
        b2 = (Button)findViewById(R.id.botaoLogin);

        loadingBar = new ProgressDialog(this);



        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                CriarConta();

                /*
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
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "E-mail já existente, inserir outro", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"As senhas não combinam", Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });
        //Redirecionar para login
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void CriarConta() {

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        String phone = e4.getText().toString();

        if ( s1.equals("") || s2.equals("") || s3.equals("") || phone.equals("") ) {
            Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
        }
        else {
            if (s2.equals(s3)) {
              //  Boolean checkEmail = db.checkEmail(s1);
               // if (checkEmail==true) {
                //    Boolean insert = db.insert(s1,s2);
                   /// if (insert==true){
                        loadingBar.setTitle("Criar conta");
                        loadingBar.setMessage("Por favor aguarde enquanto estamos checando suas credenciais");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();


                        //Se o nomeUsuario for valido, é chamado o método validar numero de telefone.
                        ValidateNomeUser(s1,s2,phone );

                        //Toast.makeText(getApplicationContext(), "Registrado com Sucesso", Toast.LENGTH_SHORT).show();
                   // }
               // }
             //  else {
                 //   Toast.makeText(getApplicationContext(), "E-mail já existente, inserir outro", Toast.LENGTH_SHORT).show();
             //   }
            }
            else {
                Toast.makeText(getApplicationContext(),"As senhas não combinam", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ValidatephoneNumber(final String s1, final String s2, final String phone)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists()) )
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("nomeUsuario", s1);
                    userdataMap.put("senha", s2);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegistrationActivity.this , "Parabéns, sua conta foi criada!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegistrationActivity.this , "Erro na rede, For favor, tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
                else {
                    Toast.makeText(RegistrationActivity.this , "Este número "+ phone + " já existe.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegistrationActivity.this , "Favor, usar outro número de telefone", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void ValidateNomeUser(final String s1, final String s2, final String phone)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(s1).exists()) )
                {
                    ValidatephoneNumber(s1,s2,phone );


                }
                else {
                    Toast.makeText(RegistrationActivity.this , "Este nome de usuário já existe.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegistrationActivity.this , "Favor, usar outro nome.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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