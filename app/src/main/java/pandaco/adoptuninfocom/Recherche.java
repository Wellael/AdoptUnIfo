package pandaco.adoptuninfocom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by lerouxca on 14/03/2016.
 */
public class Recherche extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche);

        final int nbPers = 50;

        ImageButton recherche = (ImageButton) findViewById(R.id.recherche);

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int n = rand.nextInt(nbPers);

                Reader reader = Reader.getInstance();
                

                Session.setIdPersonne(n);

                Intent goToResult = new Intent(getApplicationContext(), ProfilAutre.class);
                startActivity(goToResult);
            }
        });
    }
}