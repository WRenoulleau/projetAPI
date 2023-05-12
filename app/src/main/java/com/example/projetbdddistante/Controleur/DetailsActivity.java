package com.example.projetbdddistante.Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetbdddistante.Modele.Visiteur;
import com.example.projetbdddistante.Modele.VisiteurDAO;
import com.example.projetbdddistante.R;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private EditText editidentifiant, editnom, editprenom, editlogin, editmdp, editrue, editcp, editville, editdtembauche;
    private TextView textId;
    private String idrecu, nomrecu, prenomrecu, loginrecu, mdprecu, ruerecu, cprecu, villerecu, dtembaucherecu;
    private String identifiant, nom, prenom, login, mdp, rue, cp, ville, dtembauche;

    private Button btnRetour, btnValidate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btnRetour = (Button) findViewById(R.id.btnretour3);
        btnValidate = (Button) findViewById(R.id.buttonValiderDetails);
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        textId = (TextView) findViewById(R.id.textId);

        editnom = (EditText) findViewById(R.id.editTextNom);
        editprenom = (EditText) findViewById(R.id.editTextPre);
        editlogin = (EditText) findViewById(R.id.editTextLogin);
        editmdp = (EditText) findViewById(R.id.editTextMdp);
        editrue = (EditText) findViewById(R.id.editTextAdRue);
        editcp = (EditText) findViewById(R.id.editTextCP);
        editville = (EditText) findViewById(R.id.editTextVille);
        editdtembauche = (EditText) findViewById(R.id.editTextDate);

        idrecu=getIntent().getStringExtra("id");
        nomrecu=getIntent().getStringExtra("nom");
        prenomrecu=getIntent().getStringExtra("prenom");
        loginrecu=getIntent().getStringExtra("login");
        mdprecu=getIntent().getStringExtra("mdp");
        ruerecu=getIntent().getStringExtra("rue");
        cprecu=getIntent().getStringExtra("cp");
        villerecu=getIntent().getStringExtra("ville");
        dtembaucherecu=getIntent().getStringExtra("dtembauche");

        textId.setText(idrecu);
        editnom.setText(nomrecu);
        editprenom.setText(prenomrecu);
        editlogin.setText(loginrecu);
        editmdp.setText(mdprecu);
        editrue.setText(ruerecu);
        editcp.setText(cprecu);
        editville.setText(villerecu);
        editdtembauche.setText(dtembaucherecu);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConsultActivity.class);
                startActivity(intent);
            }
        });
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
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
            Intent i = new Intent(DetailsActivity.this, ConsultActivity.class);
            startActivity(i);
        } else {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "ERREUR : Echec de la modification du client !", Toast.LENGTH_LONG);
            msg.show();
        }
    }

    private void delete(){
        VisiteurDAO visiteurAcces = new VisiteurDAO();

        identifiant = textId.getText().toString();

        String result = visiteurAcces.deleteVisiteur(identifiant);

        Log.d("result ======",result);
        if (result.equals("true\r")) {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "Le client a bien été supprimé !", Toast.LENGTH_LONG);
            msg.show();
            Intent i = new Intent(DetailsActivity.this, ConsultActivity.class);
            startActivity(i);
        } else {
            Context c = getApplicationContext();
            Toast msg = Toast.makeText(c, "ERREUR : Echec de la suppression du client !", Toast.LENGTH_LONG);
            msg.show();
        }
    }

}