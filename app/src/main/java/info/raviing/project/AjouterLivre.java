package info.raviing.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


/**
 * Classe AjouterLivre est liée à l'activité ajouter_livre.xml qui permet d'ajouter un livre
 */
public class AjouterLivre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_livre);

        findViewById(R.id.btn_valider_ajout_livre).setOnClickListener(v -> {
            // Ajouter un livre, on prend les données des champs
            EditText titre = findViewById(R.id.input_titre);
            EditText auteur = findViewById(R.id.input_auteur);

            // Vérifier que les champs ne sont pas vides
            if(titre.getText().toString().isEmpty() || auteur.getText().toString().isEmpty()) {
                // Afficher un toast pour dire que les champs sont vides
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Ajouter le livre
                Livre livre = new Livre(titre.getText().toString(), auteur.getText().toString());
                //retourner à l'activité principale avec le livre
                Bundle bundle = new Bundle();
                bundle.putSerializable("livre", livre);
                getIntent().putExtras(bundle);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }

    /**
     * @param view bouton retour à l'activité principale
     */
    public void RetournerAccueil(View view) {
        finish();
    }
}