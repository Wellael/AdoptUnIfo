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
        final TextView adresse1=(TextView) findViewById(R.id.adresse1);
        final TextView adresse2=(TextView) findViewById(R.id.adresse2);
        final TextView adresse3=(TextView) findViewById(R.id.adresse3);
        final TextView depart=(TextView) findViewById(R.id.depart);
        final TextView anne=(TextView) findViewById(R.id.anne);

        List<String> infos = new ArrayList<String>();
        infos=Session.getInfos();

        nom.setText(String.valueOf(infos.get(0)));



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
