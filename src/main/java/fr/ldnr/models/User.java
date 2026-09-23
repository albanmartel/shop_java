package fr.ldnr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe User correspond à la table usershop présente dans la BDD SQL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int idUser;
    private String login;
    private String password;
}