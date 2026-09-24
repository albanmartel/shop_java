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
            System.out.print(this.getName());
        }
        System.out.println();
    }

    /**
     *
     * @param i indique l'indice du theard
     * @return un chaîne construite
     */
    public static String returnMotiv(int i){
        return i + "-".repeat(i) + " ";
    }

    /**
     * Méthode appelée par défaut, le point d'entrée de la classe
     * @param args Tableau de Strings
     */
    public static void main(String[] args) {
        TestThread TestThread1 = new TestThread(returnMotiv(1));
        TestThread TestThread2 = new TestThread(returnMotiv(2));
        TestThread TestThread3 = new TestThread(returnMotiv(3));
        TestThread TestThread4 = new TestThread(returnMotiv(4));
        TestThread TestThread5 = new TestThread(returnMotiv(5));
        TestThread5.start();
        TestThread4.start();
        TestThread3.start();
        TestThread2.start();
        TestThread1.start();

        /*
        Thread thirdThread = new Thread() {
            public void run() {
                System.out.println("!!! Autre ecriture!!!");
            }
        };
        thirdThread.start();
         */
    }
}
