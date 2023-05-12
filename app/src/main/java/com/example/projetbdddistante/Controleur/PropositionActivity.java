package com.example.projetbdddistante.Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetbdddistante.R;

public class PropositionActivity extends AppCompatActivity {

    private Button goToAjout, goToConsult, goToModif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        goToAjout = (Button) findViewById(R.id.goToAjout);
        goToConsult = (Button) findViewById(R.id.goToConsult);
        goToModif = (Button) findViewById(R.id.goToModif);

        goToAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), AjoutActivity.class);
                startActivity(intent);
            }
        });

        goToConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), ConsultActivity.class);
                startActivity(intent);
            }
        });
        
        goToModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), ModificationActivity.class);
                startActivity(intent);
            }
        });
    }
}