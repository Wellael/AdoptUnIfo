package pandaco.adoptuninfocom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lerouxca on 09/03/2016.
 */
public class MonProfil extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mon_profil);

        final TextView description=(TextView) findViewById(R.id.description);

        final TextView nom=(TextView) findViewById(R.id.nom);
        final TextView prenom=(TextView) findViewById(R.id.prenom);
        final TextView sexe=(TextView) findViewById(R.id.sexe);
        final TextView dateNais=(TextView) findViewById(R.id.dateNais);
        final TextView tel=(TextView) findViewById(R.id.tel);
        final TextView cp=(TextView) findViewById(R.id.cp);
        final TextView ville=(TextView) findViewById(R.id.ville);
        final TextView depart=(TextView) findViewById(R.id.depart);
        final TextView promotion=(TextView) findViewById(R.id.promotion);

        Intent intent_infos = getIntent();
        String nomEtudiant = intent_infos.getStringExtra("nom");
        String prenomEtudiant = intent_infos.getStringExtra("prenom");
        String sexeEtudiant = intent_infos.getStringExtra("sexe");
        String cpEtudiant = intent_infos.getStringExtra("cp");
        String villeEtudiant = intent_infos.getStringExtra("ville");
        String datenaissEtudiant = intent_infos.getStringExtra("date");
        String telEtudiant = intent_infos.getStringExtra("tel");
        String datePromotion = intent_infos.getStringExtra("datepromo");
        String nomDepartement = intent_infos.getStringExtra("depart");


        nom.setText(nomEtudiant);
        prenom.setText(prenomEtudiant);
        sexe.setText(sexeEtudiant);
        tel.setText(telEtudiant);
        cp.setText(cpEtudiant);
        ville.setText(villeEtudiant);
        dateNais.setText(datenaissEtudiant);
        depart.setText(nomDepartement);
        promotion.setText(datePromotion);


        Button recherche = (Button) findViewById(R.id.recherche);
        Button modif = (Button) findViewById(R.id.modif);

        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResult = new Intent(getApplicationContext(), ModifProfil.class);
                startActivity(goToResult);
            }
        });
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResult = new Intent(getApplicationContext(), Recherche.class);
                startActivity(goToResult);
            }
        });

    }


}
