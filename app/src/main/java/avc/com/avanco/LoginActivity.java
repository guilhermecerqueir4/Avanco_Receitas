package avc.com.avanco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import avc.com.avanco.model.Users;
import avc.com.avanco.prevalent.Prevalent;

public class LoginActivity extends AppCompatActivity
{
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;

    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.botaoEntrar);
        InputPassword = (EditText) findViewById(R.id.login_senha);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);


       // chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
       // Paper.init(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                parentDbName = "Admins";
                LoginButton.setText("Login Fornecedor");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);


            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                    parentDbName = "Users";
                LoginButton.setText("Login ");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);

            }
        } );
    }



    private void LoginUser()
    {
        String phone = InputPhoneNumber.getText().toString();
        String senha = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Informe seu número de telefone.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(senha))
        {
            Toast.makeText(this, "Informe sua senha.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Lendo dados..");
            loadingBar.setMessage("Por favor aguarde enquanto estamos checando suas credenciais");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            AllowAccessToAccount(phone, senha);
        }
    }



    private void AllowAccessToAccount(final String phone, final String senha)
    {
       /* if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
*/

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getSenha().equals(senha))
                        {

                            if (parentDbName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this, "Bem-vindo, "+ usersData.getNomeUsuario().toUpperCase()+" !", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class); //admin

                                startActivity(intent);
                            }
                            else if (parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "Bem-vindo, "+ usersData.getNomeUsuario().toUpperCase() + " !" , Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Telefone ou senha incorreto.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Telefone ou senha incorreto.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
