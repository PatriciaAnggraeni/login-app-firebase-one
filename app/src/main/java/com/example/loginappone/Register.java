package com.example.loginappone;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    // membuat variabel baru
    TextInputEditText inputEmail, inputPassword;
    Button btnRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // cari id-nya terlebih dahulu menggunakan method find by id
        inputEmail.findViewById(R.id.id_email);
        inputPassword.findViewById(R.id.id_password);
        btnRegister.findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();

        // selanjutnya beri listener kepada tombolnya
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // buat variabel untuk menampung nilai dari email dan password
                String email, password;

                // isikan setiap varibel dengan nilai yang dimasukkan
                email = String.valueOf(inputEmail.getText());
                password = String.valueOf(inputPassword.getText());

                // buat kondisi jika email atau password kosong
                if (TextUtils.isEmpty(email)) {
                    // tampilkan pesan di bawah
                    Toast.makeText(Register.this, "Masukkan Email!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    // tampilkan pesan di bawah
                    Toast.makeText(Register.this, "Masukkan Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // buat autetikasi database untuk pengguna baru
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Register.this, "Login Akun Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}