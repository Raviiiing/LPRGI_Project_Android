package info.raviing.project;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Classe Livre qui permet de créer la table livre
 */
@Entity
public class Livre implements Serializable {

    /**
     * Attributs de la classe Livre qui correspondent aux colonnes de la table livre
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "titre")
    private String titre;
    @ColumnInfo(name = "auteur")
    private String auteur;

    /**
     * @param titre titre du livre
     * @param auteur auteur du livre
     */
    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    /**
     * @return titre et auteur du livre, nécessaire pour l'affichage de la liste des livres
     */
    @NonNull
    @Override
    public String toString() {
        return titre + ", par " + auteur;
    }

    /**
     * @return titre du livre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre titre du livre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return auteur du livre
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * @param auteur auteur du livre
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }


    /**
     * @return id du livre
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id du livre
     */
    public void setId(int id) {
        this.id = id;
    }

}
