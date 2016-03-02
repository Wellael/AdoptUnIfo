package pandaco.adoptuninfocom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        final EditText nom=(EditText) findViewById(R.id.nom);
        final EditText prenom=(EditText) findViewById(R.id.prenom);
        final EditText dateNais=(EditText) findViewById(R.id.dateNais);
        final EditText tel=(EditText) findViewById(R.id.tel);
        final EditText mdp=(EditText) findViewById(R.id.mdp);

        Button homme=(Button) findViewById(R.id.homme);
        Button femme=(Button) findViewById(R.id.femme);
        Button hommeO=(Button) findViewById(R.id.hommeO);
        Button femmeO=(Button) findViewById(R.id.femmeO);
        Button deuxO=(Button) findViewById(R.id.deuxO);

        Button valider=(Button) findViewById(R.id.valider);
        Button retour=(Button) findViewById(R.id.retour);

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
                if(isTelValid(String.valueOf(tel.getText()))==false){
                    tel.setError("Le telephone n'est pas au bon format");
                }
                if(isDateValid(String.valueOf(dateNais.getText()))==false){
                    tel.setError("Le telephone n'est pas au bon format");
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
