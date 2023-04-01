package com.example.loginappone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    // membuat variabel baru
    TextInputEditText inputEmail, inputPassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // cari id-nya terlebih dahulu menggunakan method find by id
        inputEmail.findViewById(R.id.id_email);
        inputPassword.findViewById(R.id.id_password);
        btnLogin.findViewById(R.id.btn_login);
        progressBar.findViewById(R.id.pgr_bar);
        linkLogin.findViewById(R.id.link_register_now);

        // instansiai koneksi firebase ke android
        mAuth = FirebaseAuth.getInstance();

        // buat link untuk mengarah ke halman login
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // panggil class Intent agar bisa mengarahkan halaman ini ke login
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                // buat variabel untuk menampung nilai dari email dan password
                String email, password;

                // isikan setiap varibel dengan nilai yang dimasukkan
                email = String.valueOf(inputEmail.getText());
                password = String.valueOf(inputPassword.getText());

                // buat kondisi jika email atau password kosong
                if (TextUtils.isEmpty(email)) {
                    // tampilkan pesan di bawah
                    Toast.makeText(Login.this, "Masukkan Email!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    // tampilkan pesan di bawah
                    Toast.makeText(Login.this, "Masukkan Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}