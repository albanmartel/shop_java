package fr.ldnr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe Customer correspond à la table customer présente dans la BDD SQL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int idCustomer;
    private String name;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    
    private User user; 
}