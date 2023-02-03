package com.example.menedzerwydatkow;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menedzerwydatkow.databinding.ActivityDodajBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class dodanieInformacjiActivity extends AppCompatActivity {
    ActivityDodajBinding binding;
    private String typ;

    private EditText kwota;
    private EditText informacja;
    private EditText kategoria;

    private ProgressDialog daneProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDodajBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        typ = getIntent().getStringExtra("typ");

        if (typ == "Wydatek") {
            binding.radioWydatek.setChecked(true);
        } else {
            binding.radioPrzychod.setChecked(true);
        }

        binding.radioWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typ = "Wydatek";
            }
        });

        binding.radioPrzychod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typ = "Przychod";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.przycisk_dodaj_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idWyboru = item.getItemId();

        if (idWyboru == R.id.dodajPrzyciskMenu) {
            dodajWydatek();
            startActivity(new Intent(getApplicationContext(),stronaGlownaActivity.class));
            return true;
        }
        return false;
    }

    public void dodajWydatek() {

        kwota = findViewById(R.id.poleKwota);
        informacja = findViewById(R.id.poleInformacja);
        kategoria = findViewById(R.id.poleKategoria);


        String idInformacji = UUID.randomUUID().toString();
        String kwotaValue = kwota.getText().toString().trim();
        String informacjaValue = informacja.getText().toString().trim();
        String kategoriaValue = kategoria.getText().toString().trim();
        String rodzaj;
        boolean czyWydatek = binding.radioWydatek.isChecked();

        daneProgress = new ProgressDialog(this);

//WAŻNA METODA DODAJĄCA INFORMACJE O WYDATKU / PRZYCHODZIE DO BAZY W FIREBASE
        if (czyWydatek) {
            rodzaj = "Wydatek";
        } else {
            rodzaj = "Przychód";
        }

        InformacjeModel informacjaDoDodania = new InformacjeModel(idInformacji, kwotaValue, rodzaj, informacjaValue, kategoriaValue, FirebaseAuth.getInstance().getUid());

        if (kwota.length() == 0) {
            Toast.makeText(dodanieInformacjiActivity.this, "Pole kwota nie moze byc puste!!", Toast.LENGTH_SHORT).show();
            return;
        }


        daneProgress.setMessage("Trwa dodawanie do bazy danych...");
        daneProgress.show();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //
        db.collection("dane").document(idInformacji).set(informacjaDoDodania).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                daneProgress.dismiss();
                Toast.makeText(dodanieInformacjiActivity.this, "Dodano dane do bazy!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                daneProgress.dismiss();
                Toast.makeText(dodanieInformacjiActivity.this, "Nie udało się dodac danych do bazy.", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
