package info.raviing.project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface LivreDao {

    @Query("SELECT * FROM livre")
    List<Livre> getAll();

    @Insert
    void insert(Livre livre);
}