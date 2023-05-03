package info.raviing.project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Interface LivreDao qui définit les fonctions de la table livre
 */
@Dao
public interface LivreDao {

    /**
     * Fonction getAll qui permet de récupérer tous les livres
     * @return la liste des livres
     */
    @Query("SELECT * FROM livre")
    List<Livre> getAll();

    /**
     * Fonction insert qui permet d'insérer un livre
     * @param livre qui permet d'insérer un livre
     */
    @Insert
    void insert(Livre livre);

    /**
     * @param search qui permet de rechercher un livre
     * @return la liste des livres
     */
    @Query("SELECT * FROM livre WHERE titre LIKE '%' || :search || '%' OR auteur LIKE '%' || :search || '%'")
    List<Livre> findBySearch(String search);

}