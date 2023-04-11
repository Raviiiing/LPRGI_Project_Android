package info.raviing.project;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Livre.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LivreDao livreDao();
}
