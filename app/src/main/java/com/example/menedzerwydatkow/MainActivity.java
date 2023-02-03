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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //DEKLARACJA PRZYCISKÓW I PANELI

    private EditText logEmail;
    private EditText logPassword;
    private Button logButton;
    private TextView logTekstZarejestruj;

    private ProgressDialog logDialog;

    //ELEMENTY FIREBASE - SYSTEM BAZODANOWY
    private FirebaseAuth logAutoryzacja;


    //MAIN
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logowanieUzytkownika();
    }


    //METODA ODPOWIADAJĄCA ZA LOGOWANIE UŻYTKOWNIKA
    //ZAWIERA WALIDACJE DANYCH - HASŁO - EMAIL
    private void logowanieUzytkownika() {

        logEmail = findViewById(R.id.edtxtEmailLogowanie);
        logPassword = findViewById(R.id.edtxtHasloLogowanie);
        logButton = findViewById(R.id.btnLogowanie);
        logTekstZarejestruj = findViewById(R.id.txtRejestracja);

        logAutoryzacja = FirebaseAuth.getInstance();
        logDialog = new ProgressDialog(this);

        //METODA PO WCIŚNIĘCIU PRZYCISKU SPRAWDZA CZY WPROWADZONE DANE SĄ POPRAWNE
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = logEmail.getText().toString().trim();
                String password = logPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email nie może zostać pusty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Hasło nie może zostać puste", Toast.LENGTH_SHORT).show();
                    return;
                }

                logDialog.setMessage("Trwa logowanie...");
                logDialog.show();


                logAutoryzacja.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            Toast.makeText(MainActivity.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                            logDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), stronaGlownaActivity.class));

                        } else {
                            Toast.makeText(MainActivity.this, "Logowanie nie powiodło się", Toast.LENGTH_SHORT).show();
                            logDialog.dismiss();
                        }
                    }
                });

            }
        });


        //METODA PO NACIŚNIĘCIU PRZYCISKU OTWIERA OKNO REJESTRACJI
        logTekstZarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), rejestracjaActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser uzytkownik = FirebaseAuth.getInstance().getCurrentUser();

        if (uzytkownik == null) {
            FirebaseAuth.getInstance().signInAnonymously();
            Toast.makeText(this, "Zalogowano", Toast.LENGTH_SHORT).show();
        }
    }

}
