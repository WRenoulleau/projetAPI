package com.example.projetbdddistante.Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetbdddistante.Modele.VisiteurDAO;
import com.example.projetbdddistante.R;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private EditText editLogin, editMdp;
    private Button btnValidate;
    private TextView textresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VisiteurDAO visiteurAcces = new VisiteurDAO();

        btnValidate = (Button) findViewById(R.id.buttonConnexion);
        editLogin = (EditText) findViewById(R.id.editTextLogin);
        editMdp = (EditText) findViewById(R.id.editTextMdp);
        textresult = (TextView) findViewById(R.id.textresult);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    if(visiteurAcces.seConnecter(editLogin.getText().toString(),editMdp.getText().toString())== true){
                        Intent intent = new Intent(v.getContext(), PropositionActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if(visiteurAcces.seConnecter(editLogin.getText().toString(),editMdp.getText().toString())== false){
                        textresult.setText("Echec de la connexion");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}