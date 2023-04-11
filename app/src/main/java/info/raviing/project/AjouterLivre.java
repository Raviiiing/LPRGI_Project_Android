package info.raviing.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AjouterLivre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_livre);

        findViewById(R.id.btn_valider_ajout_livre).setOnClickListener(v -> {
            // Ajouter un livre
            EditText titre = findViewById(R.id.input_titre);
            EditText auteur = findViewById(R.id.input_auteur);

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
}