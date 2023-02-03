package com.example.menedzerwydatkow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class rejestracjaActivity extends AppCompatActivity {

    //DEKLARACJA PRZYCISKÓW I PANELI
    private EditText regEmail;
    private EditText regPassword;
    private Button regButton;
    private TextView regTekstKontoIstnieje;

    private ProgressDialog regDialog;


    //ELEMENTY FIREBASE - SYSTEM BAZODANOWY
    private FirebaseAuth regAutoryzacja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        rejestracjaUzytkownika();
    }

    //METODA ODPOWIADAJĄCA ZA REJESTRACJE UŻYTKOWNIKA
    //ZAWIERA WALIDACJE DANYCH - HASŁO - EMAIL - SIŁA HASŁA
    private void rejestracjaUzytkownika() {


        regEmail = findViewById(R.id.edtxtEmailRejestracja);
        regPassword = findViewById(R.id.edtxtHasloRejestracja);
        regButton = findViewById(R.id.btnRejestracja);
        regTekstKontoIstnieje = findViewById(R.id.textRejestracjaPosiadamKonto);
        regDialog = new ProgressDialog(this);

        //PRZYPISANIE DO ISTNIEJĄCYCH ELEMENTÓW INTERFEJSU REJESTRACJI

        //AUTORYZACJA - PRZYPISANIE
        regAutoryzacja = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailUzytkownika = regEmail.getText().toString().trim();
                String hasloUzytkownika = regPassword.getText().toString().trim();

                if (emailUzytkownika.isEmpty()) {
                    Toast.makeText(rejestracjaActivity.this, "Email jest błędny, spróbuj ponownie.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (hasloUzytkownika.isEmpty()) {
                    Toast.makeText(rejestracjaActivity.this, "Błędne hasło! Wprowadź zgodnie z instrukcją", Toast.LENGTH_SHORT).show();
                    return;
                }





                regDialog.setMessage("Przetwarzanie...");
                regDialog.show();

                regAutoryzacja.createUserWithEmailAndPassword(emailUzytkownika, hasloUzytkownika).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isComplete()) {
                            regDialog.dismiss();
                            Toast.makeText(rejestracjaActivity.this, "Rejestracja się powiodła", Toast.LENGTH_SHORT).show();
                            FirebaseUser nowyUzytkownik = regAutoryzacja.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), stronaGlownaActivity.class));
                        } else {
                            regDialog.dismiss();
                            Toast.makeText(rejestracjaActivity.this, "Rejestracja się nie powiodła", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


    }


}