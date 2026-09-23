package fr.ldnr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT IdArticle, Description, Brand, UnitaryPrice FROM T_Articles";

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
     * Recherche un article par son identifiant (IdArticle).
     */
	@Override
	public Optional<Article> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	/**
     * Insère un nouvel article en base et met à jour son IdArticle généré (AUTO_INCREMENT).
     */
	@Override
	public Article create(Article entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Met à jour un article existant.
     */
	@Override
	public boolean update(Article entity) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * Supprime un article par son ID.
     */
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
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
