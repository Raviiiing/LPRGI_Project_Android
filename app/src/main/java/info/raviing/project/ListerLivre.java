package info.raviing.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
            ListView listView = (ListView) findViewById(R.id.list);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, livres);
            listView.setAdapter(adapter);
        }else {
            //Retourner à l'activité principale
            finish();
        }
    }

    public void RetournerAccueil(View view) {
        finish();
    }
}