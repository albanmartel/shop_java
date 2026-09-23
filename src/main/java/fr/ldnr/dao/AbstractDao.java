package fr.ldnr.dao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {
    // Paramètres de connexion centralisés
    private static final String URL = "jdbc:mariadb://localhost:3306/Shop";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Ouvre et retourne une connexion JDBC.
     */
    protected Connection getConnection() throws SQLException {
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
