package fr.ldnr.dao;

/* Lectude de fichier */
import java.io.IOException;
import java.io.InputStream;
/* Gérer la liaison avec la BDD SQL */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/* Pour utiliser une liste ordonnée d'élements */
import java.util.List;
/* Permet de gérer les erreurs NUllPointerException*/
import java.util.Optional;
/* Librairier conçue pour travailler avec des dictionnaires clef / valeur */
import java.util.Properties;

public abstract class AbstractDao<T> {
	
    // Paramètres de connexion centralisés
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    
    /* Code généré par IA le 23-09-2026 */
    // Bloc statique exécuté une seule fois lors du chargement de la classe
    static {
        Properties props = new Properties();
        
        // Chargement du fichier depuis le classpath (src/main/resources/env.properties)
        try (InputStream input = AbstractDao.class.getClassLoader().getResourceAsStream("env.properties")) {
            if (input == null) {
                throw new IllegalStateException("Fichier env.properties introuvable dans le classpath.");
            }
            props.load(input);

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            // Optionnel : s'assurer que le driver MariaDB est bien chargé
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture du fichier env.properties", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC MariaDB introuvable dans le classpath", e);
        }
    }
    
    
    /**
     * Ouvre et retourne une connexion JDBC.
     */
    protected Connection getConnection() throws SQLException {
    	if (URL == null || USER == null || PASSWORD == null ) {
            throw new SQLException("Les identifiants de connexion n'ont pas été initialisés correctement.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Méthodes CRUD abstraites que chaque DAO concrète doit implémenter
    public abstract List<T> findAll();
    public abstract Optional<T> findById(int id);
    public abstract T create(T entity);
    public abstract boolean update(T entity);
    public abstract boolean delete(int id);

    /**
     * Méthode utilitaire pour convertir un ResultSet en objet Métier (T).
     */
    protected abstract T mapResultSet(ResultSet rs) throws SQLException;
}
