package com.example.menedzerwydatkow;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menedzerwydatkow.databinding.ActivityDodajBinding;
import com.example.menedzerwydatkow.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.DocumentType;

import java.util.ArrayList;
import java.util.List;

public class stronaGlownaActivity extends AppCompatActivity {


private FirebaseFirestore db;
    private TextView dodajWydatek;
    private TextView dodajPrzychod;
    private RadioButton radioWydatekZaznaczony;
    private RadioButton radioPrzychodZaznaczony;
    private Button przyciskWszystko;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strona_glowna);

        Intent intent = new Intent(stronaGlownaActivity.this, dodanieInformacjiActivity.class);
        dodajWydatek = findViewById(R.id.dodajWydatek);
        dodajPrzychod = findViewById(R.id.dodajPrzychod);
        przyciskWszystko = findViewById(R.id.bttnWyswietl);


        dodajWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), dodanieInformacjiActivity.class));
            }
        });
        dodajPrzychod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dodanieInformacjiActivity.class));
            }
        });

        przyciskWszystko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),stronaZDanymi.class));
            }
        });


    }



}