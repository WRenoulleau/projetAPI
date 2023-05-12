package com.example.projetbdddistante.Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetbdddistante.Modele.Visiteur;
import com.example.projetbdddistante.Modele.VisiteurDAO;
import com.example.projetbdddistante.R;

import org.json.JSONException;

import java.util.ArrayList;

public class ModificationActivity extends AppCompatActivity {

    private EditText editidentifiant, editnom, editprenom, editlogin, editmdp, editrue, editcp, editville, editdtembauche;
    private String identifiant, nom, prenom, login, mdp, rue, cp, ville, dtembauche;
    private Spinner spinVisiteurs;
    private TextView textId;
    private VisiteurDAO visiteurAcces;
    private ArrayList<Visiteur> listeVisiteurs = new ArrayList<Visiteur>();

    private Button btnValidate, btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);
        VisiteurDAO visiteurAcces = new VisiteurDAO();

        btnValidate = (Button) findViewById(R.id.buttonValiderModifs);
        btnRetour = (Button) findViewById(R.id.btnretour4);
        spinVisiteurs = (Spinner) findViewById(R.id.spinner);

        textId = (TextView) findViewById(R.id.textId);
        editnom = (EditText) findViewById(R.id.editTextNom);
        editprenom = (EditText) findViewById(R.id.editTextPre);
        editlogin = (EditText) findViewById(R.id.editTextLogin);
        editmdp = (EditText) findViewById(R.id.editTextMdp);
        editrue = (EditText) findViewById(R.id.editTextAdRue);
        editcp = (EditText) findViewById(R.id.editTextCP);
        editville = (EditText) findViewById(R.id.editTextVille);
        editdtembauche = (EditText) findViewById(R.id.editTextDate);

        try {
            listeVisiteurs = visiteurAcces.getVisiteurs();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<Visiteur> spinVisiteursAdapter = new ArrayAdapter<Visiteur>(this.getBaseContext(),android.R.layout.simple_spinner_item);

        for(int i=0;i<listeVisiteurs.size();i++){
            spinVisiteursAdapter.add(listeVisiteurs.get(i));
        }

        spinVisiteurs.setAdapter(spinVisiteursAdapter);
        spinVisiteurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Visiteur selectedItem= (Visiteur) spinVisiteurs.getAdapter().getItem(arg2);
                textId.setText(selectedItem.getIdentifiant());
                editnom.setText(selectedItem.getNom());
                editprenom.setText(selectedItem.getPrenom());
                editlogin.setText(selectedItem.getLogin());
                editmdp.setText(selectedItem.getMdp());
                editrue.setText(selectedItem.getRue());
                editcp.setText(selectedItem.getCp());
                editville.setText(selectedItem.getVille());
                editdtembauche.setText(selectedItem.getDtembauche());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
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


    private void update(){

        VisiteurDAO visiteurAcces = new VisiteurDAO();

        identifiant = textId.getText().toString();
        nom = editnom.getText().toString();
        prenom = editprenom.getText().toString();
        login = editlogin.getText().toString();
        mdp = editmdp.getText().toString();
        rue = editrue.getText().toString();
        cp = editcp.getText().toString();
        ville = editville.getText().toString();
        dtembauche = editdtembauche.getText().toString();

        Visiteur Entite = new Visiteur(identifiant, nom, prenom, login, mdp, rue, cp, ville, dtembauche);

        String result = visiteurAcces.updateVisiteur(Entite, identifiant, nom, prenom, login, mdp, rue, cp, ville, dtembauche);
        Log.d("result ======",result);
        if (result.equals("true\r")) {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "Le client a bien été modifié !", Toast.LENGTH_LONG);
            msg.show();
            Intent i = new Intent(ModificationActivity.this, PropositionActivity.class);
            startActivity(i);

        } else {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "ERREUR : Echec de la modification du client !", Toast.LENGTH_LONG);
            msg.show();
        }
    }

}