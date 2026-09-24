package fr.ldnr.ex3threads;

import java.awt.*;

public class TestThread extends Thread {

    /**
     *
     * @param name
     */
    public TestThread(String name) {
        super(name);
    }

    /**
     * Immplémentation de la méthode run() de l'interface TestThread
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName());
        }
    }

    /**
     * Méthode appelée par défaut, le point d'entrée de la classe
     * @param args Tableau de Strings
     */
    public static void main(String[] args) {
        TestThread firstTestThreadFirst = new TestThread("-A-");
        TestThread secondTestThreadFirst = new TestThread("----B----");
        firstTestThreadFirst.start();
        secondTestThreadFirst.start();

        Thread thirdThread = new Thread() {
            public void run() {
                System.out.println("!!! Autre ecriture!!!");
            }
        };
        thirdThread.start();
    }
}
