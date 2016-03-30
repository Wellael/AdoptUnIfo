package pandaco.adoptuninfocom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends Activity{

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


        Spinner listDep = (Spinner) findViewById(R.id.listDep);
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

}
