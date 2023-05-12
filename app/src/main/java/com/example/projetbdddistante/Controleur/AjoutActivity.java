package com.example.projetbdddistante.Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetbdddistante.Modele.Visiteur;
import com.example.projetbdddistante.Modele.VisiteurDAO;
import com.example.projetbdddistante.R;

import java.util.ArrayList;
import java.util.Iterator;

public class AjoutActivity extends AppCompatActivity {

    private EditText editidentifiant, editnom, editprenom, editlogin, editmdp, editrue, editcp, editville, editdtembauche;
    private String identifiant, nom, prenom, login, mdp, rue, cp, ville, dtembauche;

    private Button btnValidate, btnRetour;
    private Visiteur Entite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        btnValidate = (Button) findViewById(R.id.buttonValiderAjout);
        btnRetour = (Button) findViewById(R.id.btnretour2);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouter();
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PropositionActivity.class);

                startActivity(intent);
            }
        });
    }
    private void ajouter(){
        VisiteurDAO visiteurAcces = new VisiteurDAO();

        // recuperation des valeurs saisies
        editidentifiant = (EditText) findViewById(R.id.editTextId);
        editnom = (EditText) findViewById(R.id.editTextNom);
        editprenom = (EditText) findViewById(R.id.editTextPre);
        editlogin = (EditText) findViewById(R.id.editTextLogin);
        editmdp = (EditText) findViewById(R.id.editTextMdp);
        editrue = (EditText) findViewById(R.id.editTextAdRue);
        editcp = (EditText) findViewById(R.id.editTextCP);
        editville = (EditText) findViewById(R.id.editTextVille);
        editdtembauche = (EditText) findViewById(R.id.editTextDate);

        //identifiant = String.valueOf(editidentifiant);
        //nom = String.valueOf(editnom);
        //prenom = String.valueOf(editprenom);
        //login = String.valueOf(editlogin);
        //mdp = String.valueOf(editmdp);
        //rue = String.valueOf(editrue);
        //cp = String.valueOf(editcp);
        //ville = String.valueOf(editville);
        //dtembauche = String.valueOf(editdtembauche);

        identifiant = editidentifiant.getText().toString();
        nom = editnom.getText().toString();
        prenom = editprenom.getText().toString();
        login = editlogin.getText().toString();
        mdp = editmdp.getText().toString();
        rue = editrue.getText().toString();
        cp = editcp.getText().toString();
        ville = editville.getText().toString();
        dtembauche = editdtembauche.getText().toString();

        Entite = new Visiteur(identifiant, nom, prenom, login, mdp, rue, cp, ville, dtembauche);

        String result = visiteurAcces.addVisiteur(Entite);
        Log.d("result ======",result);
        if (result.equals("true\r")) {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "Le client a bien été ajouté !", Toast.LENGTH_LONG);
            msg.show();
            Intent i = new Intent(AjoutActivity.this, PropositionActivity.class);
            startActivity(i);

        } else {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "ERREUR : Echec de l'ajout du client !", Toast.LENGTH_LONG);
            msg.show();
        }
    }
}