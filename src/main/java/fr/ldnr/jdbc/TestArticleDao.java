package fr.ldnr.jdbc;

import java.io.NotActiveException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.QOM.Null;

import fr.ldnr.dao.ArticleDao;
import fr.ldnr.shop.jooq.tables.records.ArticleRecord;


/**
 * L'objectif de cette classe est de tester la classe ArticleDao et la classe Article
 */
public class TestArticleDao {
	
	public static void testCreateArticleDao(ArticleDao articleDao) {
		System.out.println("--- Test Insertion ---");
		ArticleRecord insere = articleDao.create("Clavier Mécanique", "Logitoch", new BigDecimal(89.99));
        System.out.println("Article inséré : " + insere);
	}
	
	public static void testDeleteArticleDao(ArticleDao articleDao) {
		System.out.println("--- Test de Suppression ---");
        Integer idArticle = articleDao.maxId();
        boolean suppress_art = articleDao.delete(idArticle);
        String messageString = "Suppression de l'article " + idArticle.toString();
        if (suppress_art) {
        	messageString += " a réussi";
        	articleDao.updateAutoIncrement();
        } else {
        	messageString += " a échoué";
        }
        System.out.println(messageString);
	}
	
	public static void testDisplayArticleDetails(ArticleDao articleDao) {
		System.out.println("--- Test d'affichage du détail d'un article ---");
		ArticleRecord article = articleDao.findById(1);
		String messageString = "Pas d'article à afficher";
		if (article != null) {
			messageString = article.toString();
		}
        System.out.println(messageString);
	}

	public static void main(String[] args) {
		
		// 1. Connexion JDBC classique
		Connection conn = DriverManager.getConnection(url, user, password);
		
		// 2. Création du DSLContext jOOQ
		DSLContext dsl = DSL.using(conn, SQLDialect.MARIADB);

		// 3. Instanciation de ton DAO avec le DSLContext
		ArticleDao articleDao = new ArticleDao(dsl);

		testCreateArticleDao(articleDao);
		testDeleteArticleDao(articleDao);
		testDisplayArticleDetails(articleDao);

	}

}
