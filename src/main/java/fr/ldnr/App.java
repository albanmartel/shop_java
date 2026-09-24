package fr.ldnr;

import fr.ldnr.ex3threads.TestThread;
import fr.ldnr.jdbc.TestArticleDao;

public class App {
	public static void exercice1(String[] args) {
        System.out.println("Exercice 1");
		TestArticleDao.main(args); 
	}
	
	public static void exercice3(String[] args) {
        System.out.println("Exercice 3");
		TestThread.main(args);
	}
	
    public static void main(String[] args) {
        System.out.println("Manipuler une BDD sous éclipse");
        exercice1(args);
        exercice3(args);
        System.out.println("Fin du programme !");        
    }
}
