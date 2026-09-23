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

	@Override
	public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT IdArticle, Description, Brand, UnitaryPrice FROM T_Articles";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Article article = mapResultSetToArticle(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
	}

	@Override
	public Optional<Article> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Article create(Article entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Article entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Article mapResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
    // Méthode utilitaire pour convertir une ligne de ResultSet en objet Article
    private Article mapResultSetToArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setIdArticle(rs.getInt("idArticle"));
        article.setDescription(rs.getString("description"));
        article.setBrand(rs.getString("brand"));
        article.setUnitaryPrice(rs.getBigDecimal("UnitaryPrice"));
        return article;
    }

}
