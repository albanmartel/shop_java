package fr.ldnr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe OrderItem correspond à la table customerorder_item présente dans la BDD SQL
 * sur le mcd cela correspond à l'entité associative CONTIENT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private double unitaryPrice;
    private float quantity;
    
    // Association vers l'article concerné
    private Article article;
}
