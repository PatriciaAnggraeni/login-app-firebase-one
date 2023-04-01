package com.example.loginappone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    // membuat variabel baru
    TextInputEditText inputEmail, inputPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // cari id-nya terlebih dahulu menggunakan method find by id
        inputEmail.findViewById(R.id.id_email);
        inputPassword.findViewById(R.id.id_password);
        btnRegister.findViewById(R.id.btn_register);

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
                    Toast.makeText(Register.this, "Masukkan Email!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Masukkan Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}