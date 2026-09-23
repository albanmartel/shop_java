package fr.ldnr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe qui correspond à la table categorie de la Base de données
 * Chaque paramètre private correspond à une colone de la la table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int idCategory;
    private String catName;
    private String description;

}
