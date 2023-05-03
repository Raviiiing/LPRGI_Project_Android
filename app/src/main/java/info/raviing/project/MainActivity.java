package info.raviing.project;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

/**
 * Classe MainActivity est liée à l'activité main.xml qui fait office de page d'accueil et de "carrefour" pour les autres activités
 */
public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> monLauncher;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Créer la base de données
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "projectdb").allowMainThreadQueries().build();

        // Créer le launcher pour l'activité AjouterLivre
        monLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , (resultat) -> {
                // Vérifier si l'activité AjouterLivre s'est bien terminée
                if(resultat.getResultCode() == RESULT_OK){
                    // Récupérer le livre depuis les extras
                    Intent intentRecu = resultat.getData();
                    Bundle monBundle = intentRecu.getExtras();
                    if(monBundle != null && monBundle.containsKey("livre")) {
                        // Récupérer le livre depuis les extras et l'ajouter à la base de données
                        Livre livre = (Livre) monBundle.getSerializable("livre");
                        LivreDao livreDao = db.livreDao();
                        livreDao.insert(livre);
                        Toast.makeText(this, "Livre ajouté avec succès", Toast.LENGTH_SHORT).show();
                        // Afficher la liste des livres
                        List<Livre> livres = livreDao.getAll();
                    }
                }
            }
        );
    }

    /**
     * @param view Bouton pour accéder à l'activité AjouterLivre
     */
    public void AjouterUnLivreActivity(View view) {
        Intent intent = new Intent(this, AjouterLivre.class);
        monLauncher.launch(intent);
    }

    /**
     * @param view Bouton pour accéder à l'activité ListerLivre
     */
    public void ListerLesLivresActivity(View view) {
        LivreDao livreDao = db.livreDao();
        List<Livre> livres = livreDao.getAll();
        // On passe la liste des livres en paramètre de l'intent pour pouvoir les afficher dans l'activité ListerLivre
        Intent intent = new Intent(this, ListerLivre.class);
        intent.putExtra("livres", (Serializable) livres);
        startActivity(intent);
    }
}