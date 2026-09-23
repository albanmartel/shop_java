package fr.ldnr.models;

import java.math.BigDecimal;
import fr.ldnr.models.Category;

/* POur bénéficier des getters et setters  */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe pour gérer les articles du projet Shop
 * Les paramètres correspondent aux champs des tables SQL
 */
@Data                  // Génère les getters, setters, toString(), equals() et hashCode()
@NoArgsConstructor     // Génère le constructeur sans argument
@AllArgsConstructor    // Génère le constructeur avec tous les arguments
public class Article {
    private Integer idArticle;
    private String description;
    private String brand;
    private BigDecimal unitaryPrice;
    private Category category;
}
