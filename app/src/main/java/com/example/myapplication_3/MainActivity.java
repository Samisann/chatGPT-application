package com.example.myapplication_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bLogin = findViewById(R.id.buttonLogin);
        Button bSignup = findViewById(R.id.buttonSignup);
        mAuth = FirebaseAuth.getInstance();

        bSignup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this
                                , registerActivity.class);
                        startActivity(intent);

                    }
                }
        );

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On passe a l'activité activity_2
                Intent intent = new Intent(MainActivity.this
                        , SecondActivity.class);
                startActivity(intent);
                EditText emailEditText = findViewById(R.id.editTextEmail);
                EditText passwordEditText = findViewById(R.id.editTextPassword);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                signInWithEmailAndPassword(email, password);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
          //  reload();
        }
    }



    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // l'utilisateur est connecté
            String welcomeMessage = "Welcome " + user.getEmail();
            Toast.makeText(this, welcomeMessage, Toast.LENGTH_LONG).show();
        } else {
            // l'utilisateur n'est pas connecté
            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // connexion réussie
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // connexion échouée
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

}