package pandaco.adoptuninfocom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends Activity{

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://prj001.vldi.fr/inscription.php";
    private static int ori;
    private static long iddep;

    private boolean erreur = false;

    Spinner listDep;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        final EditText nom=(EditText) findViewById(R.id.nom);
        final EditText prenom=(EditText) findViewById(R.id.prenom);
        final EditText dateNais=(EditText) findViewById(R.id.dateNais);
        final EditText tel=(EditText) findViewById(R.id.tel);
        final EditText mdp=(EditText) findViewById(R.id.mdp);
        final EditText description=(EditText) findViewById(R.id.description);
        final EditText cp = (EditText) findViewById(R.id.cp);
        final EditText ville = (EditText) findViewById(R.id.ville);

        final RadioGroup grpSex=(RadioGroup) findViewById(R.id.grpSex);
        final RadioGroup grpInter=(RadioGroup) findViewById(R.id.grpInter);

        final Button homme=(Button) findViewById(R.id.homme);
        final Button femme=(Button) findViewById(R.id.femme);
        final Button hommeO=(Button) findViewById(R.id.hommeO);
        final Button femmeO=(Button) findViewById(R.id.femmeO);
        final Button deuxO=(Button) findViewById(R.id.deuxO);

        Button valider=(Button) findViewById(R.id.valider);
        Button retour=(Button) findViewById(R.id.retour);


        final Spinner listDep = (Spinner) findViewById(R.id.listDep);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List departements = new ArrayList();
        departements.add("1- GEA");
        departements.add("2- GEII");
        departements.add("3- TC");
        departements.add("4- R&T");
        departements.add("5- QLIO");
        departements.add("6- INFO");
        departements.add("7- MPH");
        departements.add("8- GMP");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                departements
        );


        /* On definit une présentation du spinner quand il est déroulé (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner
        listDep.setAdapter(adapter);


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResult = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToResult);
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erreur=false;
                if(String.valueOf(nom.getText()).length()==0){
                    nom.setError("Veuillez entrer un nom");
                    erreur =true;
                }
                if(String.valueOf(prenom.getText()).length()==0){
                    prenom.setError("Veuillez entrer un prenom");
                    erreur =true;
                }
                if(isDateValid(String.valueOf(dateNais.getText()))==false){
                    dateNais.setError("La date n'est pas au bon format");
                    erreur =true;
                }
                if(homme==null && femme==null) {
                    homme.setError("Vous devez au moin choisir un sexe");
                    erreur =true;
                }
                if(isTelValid(String.valueOf(tel.getText()))==false){
                    tel.setError("Le telephone n'est pas au bon format");
                    erreur =true;
                }
                if(String.valueOf(mdp.getText()).length()==0){
                    mdp.setError("Veuillez entrer un mot de passe");
                    erreur =true;
                }
                if(hommeO==null && femmeO==null && deuxO==null){
                    deuxO.setError("Vous devez au moin choisir un interet");
                    erreur =true;
                }
                if(!erreur){
                    Intent goToResult = new Intent(getApplicationContext(), ModifProfil.class);
                    startActivity(goToResult);


                }

                iddep=listDep.getSelectedItemId();

                String sexe;


                int idsex = grpSex.getCheckedRadioButtonId();
                if (idsex == R.id.homme){
                    sexe = "M";
                }
                else{
                    sexe = "F";
                }

                int idori = grpInter.getCheckedRadioButtonId();
                if (idori == R.id.hommeO){
                    ori = 1;
                }
                else if(idori == R.id.femmeO){
                    ori = 2;
                }
                else{
                    ori = 3;
                }

                Etudiant etudiant = new Etudiant();
                etudiant.setNom_etudiant(nom.getText().toString());
                etudiant.setPrenom_etudiant(prenom.getText().toString());
                etudiant.setTel_etudiant(tel.getText().toString());
                etudiant.setMdp_etudiant(mdp.getText().toString());
                etudiant.setDatenaiss(dateNais.getText().toString());
                etudiant.setDescription_etudiant(description.getText().toString());
                etudiant.setSexe_etudiant(sexe);
                etudiant.setCp(cp.getText().toString());
                etudiant.setVille(ville.getText().toString());



                WebService webService = new WebService();
                //On appel l'AsyncTask
                webService.execute(etudiant);//Appel de l'asyntask
            }
        });

    }

    public boolean isTelValid(String tel){

        String pattern="^0[0-9]([0-9]{2}){4}$";
        Pattern regEx = Pattern.compile(pattern);
        final Matcher m = regEx.matcher(tel);

        if(m.find()){
            return true;
        }else{
            return false;
        }

    }

    public boolean isDateValid(String mail){

        String pattern="^([0-3][0-9])+/+([0-1][0-9])+/+([0-9]{4})$";
        Pattern regEx = Pattern.compile(pattern);
        final Matcher m = regEx.matcher(mail);

        if(m.find()){
            return true;
        }else{
            return false;
        }

    }

    //WebService est une classe interne privee qui permet d'effectuer des operations gourmandes en ressources
    //dans un processus separe du MAIN UI (processus principale qui gere les elements des vues et les evenements associes.
    //Un AsyncTask permet d'eviter que le MAIN UI soit surcharge et que l'appli freeze ou plante (dans le pire des cas).
    private class WebService extends AsyncTask<Etudiant, Integer, JSONObject> {

        //Actions a effectuer avant toutes operations
        @Override
        protected void onPreExecute() {
            Log.i("preexecute", "la c'est bon");
            super.onPreExecute();
        }

        @Override // THE METHODE
        protected JSONObject doInBackground(Etudiant... params) {
            // executer ici la tache en toile de fond
            // preparation de la connexion

            JSONObject jsonObject = new JSONObject();

            try {
                Log.i("jsais pas", connect(params[0]).toString());
                return connect(params[0]);//params[0] est l etudiant
            } catch (IOException e) {
                Log.i("jsais pas2", jsonObject.toString());
                return jsonObject;
            }


        }

        //Cette methode s'effectue apres les operations de doInBackground.
        //Elle permet d'interagir avec le MAIN UI.
        //Elle est souvent utilisee pour afficher le resultat.
        @Override
        protected void onPostExecute(JSONObject responseJson) {
            // code de finalisation (mise a jour IHM, etc.)
            // check if connection status is OK

            try {

                //On recupere l'etat et le message retourne en JSON
                //Le JSON est donc deserialise
                int loginOK = responseJson.getInt(FLAG_SUCCESS);
                Log.i("hein", responseJson.toString());
                Toast.makeText(getApplicationContext(), responseJson.getString(FLAG_MESSAGE),
                        Toast.LENGTH_SHORT).show();


                if (loginOK != 0) {
                    Toast.makeText(getApplicationContext(), "Profil creer",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Try again.",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Log.i("putain", "pffff");
                Toast.makeText(getApplicationContext(), responseJson.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private JSONObject connect(Etudiant etudiant) throws IOException {
        JSONObject jsonResponse= new JSONObject();
        try {
            Log.i("connect", "hzahahhzah");
            InputStream is = null;

            //on cree une connection
            URL url = new URL(LOGIN_URL);
            Log.i("url", url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //on defini que la methode sera Post
            conn.setRequestMethod("POST");

            //On cree la chaine de donnee a passer
            String urlParameters  = "nom_etudiant="+etudiant.getNom_etudiant()+"&prenom_etudiant="+etudiant.getPrenom_etudiant()+"&sexe_etudiant="+etudiant.getSexe_etudiant()+"&cp="+etudiant.getCp()+"&ville="+etudiant.getVille()+"&description_etudiant="+etudiant.getDescription_etudiant()+"&datenaiss="+etudiant.getDatenaiss()+"&tel_etudiant="+etudiant.getTel_etudiant()+"&mdp_etudiant="+etudiant.getMdp_etudiant()+"&id_orientation"+ori+"&id_departement"+iddep;

            Log.i("param", urlParameters);


            //on encode
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8); //on decoupe en octets avec le charset UTf8
            conn.setRequestProperty("Content-Length", "" + postData.length);
            Log.i("azea", postData.toString());

            //On envoie les donnees
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                Log.i("del", "dejgl");
                wr.write( postData );
            } catch (Exception e) {
                Log.i("mais pk ?", "je sais pas vraiment");
            }


            //open
            conn.connect();


            // on decode la reponse
            InputStream in = new BufferedInputStream(conn.getInputStream());
            jsonResponse = new JSONObject(convertStreamToString(in));
            //clean up

            is.close();
            conn.disconnect();


            return jsonResponse;
        } catch (Exception e) {
            Log.i("alors", "et mer...");
            e.printStackTrace();

            return jsonResponse;
        }
    }

    public String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
