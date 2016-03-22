package pandaco.adoptuninfocom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Connexion extends Activity{

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String LOGIN_URL = "http://prj001.vldi.fr/"; // ajustez selon votre adresse de serveur



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        Button co = (Button) findViewById(R.id.connexion);
        Button retour = (Button) findViewById(R.id.retour);

        final EditText tel = (EditText) findViewById(R.id.tel);
        final EditText mdp = (EditText) findViewById(R.id.mdp);

        co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTelValid(String.valueOf(tel.getText())) == false) {
                    Log.i("quoi ???", "oh putain ...");
                    tel.setError("Le telephone n'est pas au bon format");
                } else {

                    Etudiant etudiant = new Etudiant();
                    Log.i("tel", tel.getText().toString());
                    etudiant.setTel_etudiant(tel.getText().toString());
                    Log.i("mdp", mdp.getText().toString());
                    etudiant.setMdp_etudiant(mdp.getText().toString());
                    Log.i("etudiant", "kfldsfke");

                    //On instancie on objet de type WebService
                    WebService webService = new WebService();
                    //On appel l'AsyncTask
                    webService.execute(etudiant);//Appel de l'asyntask
                }
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResult = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToResult);
            }
        });

    }

    public boolean isTelValid(String tel) {

        String pattern = "^0[0-9]([0-9]{2}){4}$";
        Pattern regEx = Pattern.compile(pattern);
        final Matcher m = regEx.matcher(tel);

        if (m.find()) {
            return true;
        } else {
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
                    Toast.makeText(getApplicationContext(), "Connecte",
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
            String urlParameters  = "tel_etudiant="+etudiant.getTel_etudiant()+"&mdp_etudiant="+etudiant.getMdp_etudiant();
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
