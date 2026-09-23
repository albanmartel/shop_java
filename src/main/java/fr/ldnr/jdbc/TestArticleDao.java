package fr.ldnr.jdbc;

import java.math.BigDecimal;

import fr.ldnr.dao.ArticleDao;
import fr.ldnr.models.Article;

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

	public static void main(String[] args) {
		ArticleDao articleDao = new ArticleDao();
		testCreateArticleDao(articleDao);

	}

}
