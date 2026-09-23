package fr.ldnr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.ldnr.models.Article;

/**
 * Classe pour faire la liaison entre les objets Poo et la BDD
 * ArticleDao s'occupe de la classe Article
 */
public class ArticleDao extends AbstractDao<Article> {

	/**
     * Récupère la liste de tous les articles.
     */
	@Override
	public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT idArticle, description, brand, unitaryPrice FROM article;";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Article article = mapResultSet(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
	}

	/**
     * Recherche un article par son identifiant (idArticle).
     */
	@Override
	public Optional<Article> findById(int idArticle) {
        String sql = "SELECT idArticle, description, brand, unitaryPrice FROM article WHERE idArticle = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idArticle);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
	
	/**
     * Insère un nouvel article en base et met à jour son IdArticle généré (AUTO_INCREMENT).
     */
	@Override
	public Article create(Article entity) {
        String sql = "INSERT INTO articles (description, brand, unitaryPrice) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getDescription());
            stmt.setString(2, entity.getBrand());
            stmt.setBigDecimal(3, entity.getUnitaryPrice());

            stmt.executeUpdate();

            // Récupération de l'ID généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setIdArticle(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

	/**
     * Met à jour un article existant.
     */
	@Override
	public boolean update(Article article) {
        String sql = "UPDATE article SET description = ?, brand = ?, unitaryPrice = ? WHERE idArticle = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, article.getDescription());
            stmt.setString(2, article.getBrand());
            stmt.setBigDecimal(3, article.getUnitaryPrice());
            stmt.setInt(4, article.getIdArticle());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	/**
     * Supprime un article par son ID.
     */
	@Override
	public boolean delete(int idArticle) {
		String sql = "DELETE FROM article WHERE idArticle = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idArticle);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	/**
     * Méthode utilitaire pour convertir une ligne de ResultSet en objet Article.
     * Implémentation de la méthode abstraite définie dans AbstractDao.
     */
	@Override
	protected Article mapResultSet(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setIdArticle(rs.getInt("idArticle"));
        article.setDescription(rs.getString("description"));
        article.setBrand(rs.getString("brand"));
        article.setUnitaryPrice(rs.getBigDecimal("UnitaryPrice"));
        return article;
    }

}
