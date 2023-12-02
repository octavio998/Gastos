package com.example.gastos;

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

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnCuenta;
    private EditText editTextNombre;
    private EditText editTextPassword;
    public void register(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent2 = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent2);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        btnCuenta = findViewById(R.id.btnCuenta);
        editTextNombre = findViewById(R.id.textUser);
        editTextPassword = findViewById(R.id.textPassword);

        btnCuenta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String valorEditTextNombre = editTextNombre.getText().toString();
                String valorEditTextPassword = editTextPassword.getText().toString();

                register(valorEditTextNombre, valorEditTextPassword);


            }
        });


    }
}
