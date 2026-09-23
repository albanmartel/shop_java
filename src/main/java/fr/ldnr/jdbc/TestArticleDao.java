package fr.ldnr.jdbc;

import java.math.BigDecimal;

import fr.ldnr.dao.ArticleDao;
import fr.ldnr.models.Article;
import lombok.ToString;

/**
 * L'objectif de cette classe est de tester la classe ArticleDao et la classe Article
 */
public class TestArticleDao {
	
	public static void testCreateArticleDao(ArticleDao articleDao) {
		System.out.println("--- Test Insertion ---");
        Article nouveauArticle = new Article();
        nouveauArticle.setDescription("Clavier Mécanique");
        nouveauArticle.setBrand("Logitoch");
        nouveauArticle.setUnitaryPrice(new BigDecimal(89.99));
        Article insere = articleDao.create(nouveauArticle);
        System.out.println("Article inséré : " + insere);
	}
	
	public static void testDeleteArticleDao(ArticleDao articleDao) {
		System.out.println("--- Test de Suppression ---");
        Integer idArticle = articleDao.maxId();
        boolean suppress_art = articleDao.delete(idArticle);
        String messageString = "Suppression de l'article " + idArticle.toString();
        if (suppress_art) {
        	messageString += " a réussi";
        } else {
        	messageString += " a échoué";
        }
        System.out.println(messageString);
	}

	public static void main(String[] args) {
		ArticleDao articleDao = new ArticleDao();
		testCreateArticleDao(articleDao);
		testDeleteArticleDao(articleDao);

	}

}
