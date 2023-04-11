package info.raviing.project;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> monLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , (resultat) -> {
                if(resultat.getResultCode() == RESULT_OK){
                    Intent intentRecu = resultat.getData();
                    Bundle monBundle = intentRecu.getExtras();
                    if(monBundle != null && monBundle.containsKey("livre")) {
                        Livre livre = (Livre) monBundle.getSerializable("livre");
                        System.out.println(livre);
                        //TODO: ajouter le livre à la bd
                    }
                }
            }
        );
    }

    public void AjouterUnLivreActivity(View view) {
        Intent intent = new Intent(this, AjouterLivre.class);
        monLauncher.launch(intent);
    }
}