package com.example.projetbdddistante.Modele;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class VisiteurDAO {

    public VisiteurDAO() {
    }


    public String addVisiteur(Visiteur unVisiteur) {
        String result = "";
        //adresse de l'URL de l\'API à interroger et fichier php permettant d'\ajouter le visiteur
        String myUrl="https://sjpslam.alwaysdata.net/willem/API/addVisiteur.php";
        //informations à transmettre pour effectuer l'ajout
        String params =
                "id="+unVisiteur.getIdentifiant()+"&nom="+unVisiteur.getNom()+
                "&prenom="+unVisiteur.getPrenom()+
                "&login="+unVisiteur.getLogin()+
                "&mdp="+unVisiteur.getMdp()+
                "&adresse="+unVisiteur.getRue()+
                "&cp="+unVisiteur.getCp()+
                "&ville="+unVisiteur.getVille()+
                "&dateEmbauche="+unVisiteur.getDtembauche();
        Log.d("requete",params);

        HttpPostRequest postRequest = new HttpPostRequest();
        try{
            result = postRequest.execute(new String []{myUrl, params}).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("resultat",result);
        return result;
    }

    public ArrayList<Visiteur> getVisiteurs() throws JSONException {
        String result = "";
        String params="";

        ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();

        String myUrl="https://sjpslam.alwaysdata.net/willem/API/getVisiteurs.php";

        Log.d("requete",myUrl);

        HttpPostRequest postRequest = new HttpPostRequest();
        try{
            result = postRequest.execute(new String []{myUrl, params}).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("resultat",result);

        JSONArray arrayV = new JSONArray(result);
        for(int i = 0;i<arrayV.length();i++) {
            String id = arrayV.getJSONObject(i).getString("id");
            String nom = arrayV.getJSONObject(i).getString("nom");
            String prenom = arrayV.getJSONObject(i).getString("prenom");
            String login = arrayV.getJSONObject(i).getString("login");
            String mdp = arrayV.getJSONObject(i).getString("mdp");
            String adresse = arrayV.getJSONObject(i).getString("adresse");
            String cp = arrayV.getJSONObject(i).getString("cp");
            String ville = arrayV.getJSONObject(i).getString("ville");
            String dateEmbauche = arrayV.getJSONObject(i).getString("dateEmbauche");

            lesVisiteurs.add(new Visiteur(id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche));
            Log.d("------------",arrayV.get(i).toString());
        }
        return lesVisiteurs;
    }

    public String updateVisiteur(Visiteur unVisiteur, String identifiant, String nom, String prenom, String login, String mdp, String rue, String cp, String ville, String dtembauche){
        String result = "";

        String myUrl="https://sjpslam.alwaysdata.net/willem/API/modifVisiteurByIdV.php";
        Log.d("requete",myUrl);

        String params =
                        "id="+identifiant+"&nom="+nom+
                        "&prenom="+prenom+
                        "&login="+login+
                        "&mdp="+mdp+
                        "&adresse="+rue+
                        "&cp="+cp+
                        "&ville="+ville+
                        "&dateEmbauche="+dtembauche+
                        "&oldIdV="+unVisiteur.getIdentifiant();

        unVisiteur.setIdentifiant(identifiant);
        unVisiteur.setNom(nom);
        unVisiteur.setPrenom(prenom);
        unVisiteur.setLogin(login);
        unVisiteur.setMdp(mdp);
        unVisiteur.setRue(rue);
        unVisiteur.setCp(cp);
        unVisiteur.setVille(ville);
        unVisiteur.setDtembauche(dtembauche);

        HttpPostRequest postRequest = new HttpPostRequest();
        try{
            result = postRequest.execute(new String []{myUrl, params}).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("resultat",result);
        return result;
    }

    public String deleteVisiteur(String identifiant){
        String result = "";

        String myUrl="https://sjpslam.alwaysdata.net/willem/API/supVisiteurByIdV.php";
        Log.d("requete",myUrl);

        String params = "id="+identifiant;
        Log.d("requete",params);

        HttpPostRequest postRequest = new HttpPostRequest();
        try{
            result = postRequest.execute(new String []{myUrl, params}).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("resultat",result);
        return result;
    }

    public boolean seConnecter(String unLogin, String unMdp) throws JSONException {
        ArrayList<Visiteur> liste = this.getVisiteurs();
        for (Visiteur item : liste){
            if (item.getLogin().equals(unLogin) && item.getMdp().equals(unMdp)) {
                return (true);
            }
        }
        return(false);
    }
}
