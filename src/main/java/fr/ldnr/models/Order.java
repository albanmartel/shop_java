package fr.ldnr.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Order correspond à la table customerorder présente dans la BDD SQL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int idOrder;
    private double amount;
    private LocalDate dateOrder;
    
    // Référence vers le client (association COMMANDER)
    private Customer customer;
    
    // Liste des lignes de la commande (classe d'association CONTIENT)
    private List<OrderItem> items = new ArrayList<>();
}