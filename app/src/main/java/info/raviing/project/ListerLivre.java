package info.raviing.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class ListerLivre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_livre);

        // Récupérer la liste des livres depuis les extras
        // Afficher la liste des livres
        if(getIntent().hasExtra("livres")) {
            List<Livre> livres = (List<Livre>) getIntent().getSerializableExtra("livres");

        }else {
            //Retourner à l'activité principale
            finish();
        }
    }
}