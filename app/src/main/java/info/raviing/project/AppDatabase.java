package info.raviing.project;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/** Classe AppDatabase qui permet de créer la base de données */
@Database(entities = {Livre.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /** Fonction livreDao qui permet de créer la table livre */
    public abstract LivreDao livreDao();
}
