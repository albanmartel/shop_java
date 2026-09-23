package fr.ldnr;

import fr.ldnr.jdbc.TestArticleDao;

public class App {
    public static void main(String[] args) {
        System.out.println("Manipuler une BDD sous éclipse");
        TestArticleDao.main(args); 
        System.out.println("Fin du programme !");        
    }
}
