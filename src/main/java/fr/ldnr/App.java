package fr.ldnr;

import fr.ldnr.jdbc.TestArticleDao;
import fr.ldnr.ex3threads.ThreadTime;

public class App {
	public static void exercice1(String[] args) {
		TestArticleDao.main(args); 
	}
	
	public static void exercice3(String[] args) {
		ThreadTime.main(args); 
	}
	
    public static void main(String[] args) {
        System.out.println("Manipuler une BDD sous éclipse");
        exercice1(args);
        exercice3(args);
        System.out.println("Fin du programme !");        
    }
}
