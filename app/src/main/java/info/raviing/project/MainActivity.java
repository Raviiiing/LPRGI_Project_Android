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

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> monLauncher;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "projectdb").allowMainThreadQueries().build();

        monLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , (resultat) -> {
                if(resultat.getResultCode() == RESULT_OK){
                    Intent intentRecu = resultat.getData();
                    Bundle monBundle = intentRecu.getExtras();
                    if(monBundle != null && monBundle.containsKey("livre")) {
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

    public void AjouterUnLivreActivity(View view) {
        Intent intent = new Intent(this, AjouterLivre.class);
        monLauncher.launch(intent);
    }

    public void ListerLesLivresActivity(View view) {
        LivreDao livreDao = db.livreDao();
        List<Livre> livres = livreDao.getAll();
        Intent intent = new Intent(this, ListerLivre.class);
        intent.putExtra("livres", (Serializable) livres);
        startActivity(intent);
    }
}