package pandaco.adoptuninfocom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lerouxca on 14/03/2016.
 */
public class ProfilAutre extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_autre);

        final TextView description=(TextView) findViewById(R.id.description);

        final TextView nom=(TextView) findViewById(R.id.nom);
        final TextView prenom=(TextView) findViewById(R.id.prenom);
        final TextView sexe=(TextView) findViewById(R.id.sexe);
        final TextView dateNais=(TextView) findViewById(R.id.dateNais);
        final TextView depart=(TextView) findViewById(R.id.depart);
        final TextView anne=(TextView) findViewById(R.id.anne);

        Button avoirNum = (Button) findViewById(R.id.avoirNum);
        Button retour = (Button) findViewById(R.id.retour);

        nom.setText(String.valueOf(Session.getIdPersonne()));

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToResult = new Intent(getApplicationContext(), Recherche.class);
                startActivity(goToResult);
            }
        });
    }
}
