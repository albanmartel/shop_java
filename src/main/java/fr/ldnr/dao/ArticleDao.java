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

import static fr.ldnr.shop.jooq.Tables.ARTICLE;
import fr.ldnr.shop.jooq.tables.records.ArticleRecord;
import org.jooq.DSLContext;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe pour faire la liaison entre les objets Poo et la BDD
 * ArticleDao s'occupe de la classe Article
 */
public class ArticleDao {
	
	private final DSLContext dsl;

    public ArticleDao(DSLContext dsl) {
        this.dsl = dsl;
    }

	/**
     * Récupère la liste de tous les articles.
     * @return une liste de ArticleRecord
     */
	public List<ArticleRecord> findAll() {
        return dsl.selectFrom(ARTICLE)
                  .fetch();
    }

	/**
     * Recherche un article par son identifiant (idArticle).
     */
	public ArticleRecord findById(int id) {
        return dsl.selectFrom(ARTICLE)
                  .where(ARTICLE.ID_ARTICLE.eq(id))
                  .fetchOne();
    }
	
	/**
     * Insère un nouvel article en base et met à jour son IdArticle généré (AUTO_INCREMENT).
     */
	public void create(String description, String brand, BigDecimal price) {
        dsl.insertInto(ARTICLE)
           .set(ARTICLE.DESCRIPTION, description)
           .set(ARTICLE.BRAND, brand)
           .set(ARTICLE.UNITARY_PRICE, price)
           .execute();
    }

	/**
     * Met à jour un article existant.
     */
	public void update(ArticleRecord article) {
        article.attach(dsl.configuration()); // Attache le record à la BDD
        article.store(); // Met à jour automatiquement en BDD
    }

	/**
     * Supprime un article par son ID.
     */
	@Override
	public boolean delete(int idArticle) {
		String sql = "DELETE FROM article WHERE idArticle = ?";
            return false;
    }

	/**
     * Méthode utilitaire pour convertir une ligne de ResultSet en objet Article.
     * Implémentation de la méthode abstraite définie dans AbstractDao.
     */
	@Override
	protected Article mapResultSet(ResultSet rs) throws SQLException {
        Article article = new Article();
        return article;
    }
	
	/**
	 * Permet de connaître l'id max de la table article.
	 * @return L'id maximum, ou 0 si la table est vide / en cas d'erreur.
	 */
	public int maxId() {
	    String sql = "SELECT COALESCE(MAX(idArticle), 0) AS max_id FROM article";

	    return 0;
	}
	
	/**
	 * Aligne la valeur de l'AUTO_INCREMENT sur le maxId() + 1 (ou 1 si la table est vide).
	 * @return true si la modification a réussi, false sinon.
	 */
	public boolean updateAutoIncrement() {
	        return false;
	}
}
