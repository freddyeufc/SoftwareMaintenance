package com.carlos.manutencao.ifome.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.carlos.manutencao.ifome.R;
import com.carlos.manutencao.ifome.dao.DaoUser;
import com.carlos.manutencao.ifome.firebase.FirebaseAuthConfig;
import com.carlos.manutencao.ifome.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextName;
    private User user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginButton = findViewById(R.id.loginWithEmailButtonRegisterId);
        editTextLogin = findViewById(R.id.editTextLoginRegisterId);
        editTextPassword = findViewById(R.id.editTextPasswordRegisterId);
        editTextName = findViewById(R.id.editTextNameRegisterId);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
    }

    private void createNewUser(){
        final String login = editTextLogin.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String name = editTextName.getText().toString();

        if (!login.isEmpty() && !password.isEmpty() && !name.isEmpty()) {

            auth = FirebaseAuthConfig.getFirebaseAuth();
            auth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(RegisterActivity.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser firebaseUser = task.getResult().getUser();

                                user = new User(firebaseUser.getUid(), name, login, password);

                                if (DaoUser.saveUser(user)) {
                                    Toast.makeText(getApplicationContext(), "Usuário Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Log.i("Error on save user", task.getException().toString());
                            }
                        }
                    });

        } else {
            Toast.makeText(getApplicationContext(), "Todos os Campos devem ser preenchidos!", Toast.LENGTH_LONG).show();
        }
    }
}
