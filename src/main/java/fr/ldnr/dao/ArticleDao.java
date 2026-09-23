package fr.ldnr.dao;

import fr.ldnr.models.Article;

import static fr.ldnr.shop.jooq.Tables.ARTICLE;
import fr.ldnr.shop.jooq.tables.records.ArticleRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

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
                  .where(ARTICLE.IDARTICLE.eq(id))
                  .fetchOne();
    }
	
	/**
     * Insère un nouvel article en base et met à jour son IdArticle généré (AUTO_INCREMENT).
     */
	public ArticleRecord create(String description, String brand, BigDecimal price) {
        dsl.insertInto(ARTICLE)
           .set(ARTICLE.DESCRIPTION, description)
           .set(ARTICLE.BRAND, brand)
           .set(ARTICLE.UNITARYPRICE, price)
           .returning()
           .fetchOne();
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
	public boolean delete(int id) {
		int rowsDeleted = dsl.deleteFrom(ARTICLE)
           .where(ARTICLE.IDARTICLE.eq(id))
           .execute();
        return rowsDeleted > 0;
    }


	/**
     * Permet de connaître l'id max de la table article.
     * @return L'id maximum, ou 0 si la table est vide / en cas d'erreur.
     */
    public int maxId() {
        Integer max = dsl.select(DSL.coalesce(ARTICLE.IDARTICLE.max(), 0))
                         .from(ARTICLE)
                         .fetchOneInto(Integer.class);
                         
        return max != null ? max : 0;
    }
	
    /**
     * Aligne la valeur de l'AUTO_INCREMENT sur le maxId() + 1.
     * @return true si la modification a réussi, false sinon.
     */
    public boolean updateAutoIncrement() {
        try {
            int nextId = maxId() + 1;
            // Utilisation de query brute sécurisée via jOOQ
            dsl.query("ALTER TABLE article AUTO_INCREMENT = ?", nextId).execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
