package info.raviing.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Classe ListerLivre est liée à l'activité lister_livre.xml qui permet de lister les livres
 */
public class ListerLivre extends AppCompatActivity {

    Context context = this;
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

    /**
     * @param view Bouton Retourner à l'accueil
     */
    public void RetournerAccueil(View view) {
        finish();
    }

    /**
     * Permet de créer le menu de recherche dans la barre d'action et de gérer les événements
     * @param menu Menu de recherche dans la barre d'action
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Créer le menu de recherche dans la barre d'action
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Gérer les événements
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        context = this;
        // Gérer les événements de la SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Appelé lorsque l'utilisateur soumet la requête et affiche les résultats de la recherche
             * @param query Texte de la recherche soumis par l'utilisateur
             * @return true
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Traitement de la recherche lorsque l'utilisateur soumet la requête
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "projectdb").allowMainThreadQueries().build();
                LivreDao livreDao = db.livreDao();
                List<Livre> livres = livreDao.findBySearch(query);
                ListView listView = (ListView) findViewById(R.id.list);
                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, livres);
                listView.setAdapter(adapter);
                return true;
            }

            /**
             * Appelé lorsque l'utilisateur modifie le texte de la recherche et affiche les résultats de la recherche
             * @param newText Texte de la recherche à chaque changement de texte dans la SearchView
             * @return true
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                // Traitement de la recherche à chaque changement de texte dans la SearchView
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "projectdb").allowMainThreadQueries().build();
                LivreDao livreDao = db.livreDao();
                List<Livre> livres = livreDao.findBySearch(newText);
                ListView listView = (ListView) findViewById(R.id.list);
                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, livres);
                listView.setAdapter(adapter);
                return true;
            }
        });

        return true;
    }

}