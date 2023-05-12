package com.example.projetbdddistante.Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.projetbdddistante.Modele.Visiteur;
import com.example.projetbdddistante.Modele.VisiteurDAO;
import com.example.projetbdddistante.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ConsultActivity extends AppCompatActivity {

    private ListView listVisiteur;
    private ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        btnRetour = (Button) findViewById(R.id.btnretour);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PropositionActivity.class);
                startActivity(intent);
            }
        });

        listVisiteur = (ListView) findViewById(R.id.listeVisiteurs);
        VisiteurDAO visiteurAcces = new VisiteurDAO();

        try {
            lesVisiteurs = visiteurAcces.getVisiteurs();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter monAdapter = new ArrayAdapter(ConsultActivity.this,android.R.layout.simple_list_item_1,lesVisiteurs);

        listVisiteur.setAdapter(monAdapter);

        listVisiteur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Visiteur selectedItem= (Visiteur) listVisiteur.getAdapter().getItem(position);
                Intent i = new Intent(ConsultActivity.this,DetailsActivity.class);
                i.putExtra("pos", position);
                i.putExtra("id",selectedItem.getIdentifiant());
                i.putExtra("nom",selectedItem.getNom());
                i.putExtra("prenom",selectedItem.getPrenom());
                i.putExtra("login",selectedItem.getLogin());
                i.putExtra("mdp",selectedItem.getMdp());
                i.putExtra("rue",selectedItem.getRue());
                i.putExtra("cp",selectedItem.getCp());
                i.putExtra("ville",selectedItem.getVille());
                i.putExtra("dtembauche",selectedItem.getDtembauche());
                startActivity(i);
            }
        });
    }
}