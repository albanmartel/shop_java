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
    public static String returnFirstMotiv(int i) {
        /* Méthode Java 11 */
        //return i + "-".repeat(i) + " ";

        /* Méthode avant Java 11 */
        String motiv = String.valueOf(i);
        for (int j = 0; j < i; j++) {
            motiv += "-";
        }
        return motiv + " ";
    }

    /**
     *
     * @param i indique l'indice du theard
     * @return un chaîne construite
     */
    public static String returnSecondMotiv(String beginEndCharacter, int i) {
        /* Méthode Java 11 */
        //return i + "-".repeat(i) + " ";

        /* Méthode avant Java 11 */
        String motiv = beginEndCharacter;
        for (int j = 0; j < i; j++) {
            motiv += "*";
        }
        motiv += beginEndCharacter;

        return motiv;
    }


    /**
     * Méthode déléguée du main
     */
    public static void firstThreadExecution() {
        TestThread TestThread1 = new TestThread(returnFirstMotiv(1));
        TestThread TestThread2 = new TestThread(returnFirstMotiv(2));
        TestThread TestThread3 = new TestThread(returnFirstMotiv(3));
        TestThread TestThread4 = new TestThread(returnFirstMotiv(4));
        TestThread TestThread5 = new TestThread(returnFirstMotiv(5));
        TestThread5.start();
        TestThread4.start();
        TestThread3.start();
        TestThread2.start();
        TestThread1.start();
        /*
        Thread thirdThread = new Thread() {
            public void run() {
                System.out.println("!!! Autre écriture!!!");
            }
        };
        thirdThread.start();
        */
    }

    /**
     * Méthode pour répondre à la quesion
     */
    public static void secondthreadExecution() {
        TestThread TestThread1 = new TestThread(returnSecondMotiv("!", 1));
        TestThread TestThread2 = new TestThread(returnSecondMotiv("\"", 2));
        TestThread TestThread3 = new TestThread(returnSecondMotiv("#", 3));
        TestThread TestThread4 = new TestThread(returnSecondMotiv("$", 4));
        TestThread TestThread5 = new TestThread(returnSecondMotiv("%", 5));
        TestThread TestThread6 = new TestThread(returnSecondMotiv("&", 6));
        TestThread TestThread7 = new TestThread(returnSecondMotiv("'", 7));
        TestThread TestThread8 = new TestThread(returnSecondMotiv("(", 8));
        TestThread TestThread9 = new TestThread(returnSecondMotiv(")", 9));
        TestThread TestThread10 = new TestThread(returnSecondMotiv("*", 10));


        TestThread1.start();

        try {
            TestThread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestThread2.start();

        try {
            TestThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestThread3.start();

        try {
            TestThread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            TestThread4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        TestThread5.start();

        try {
            TestThread5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        TestThread6.start();

        try {
            TestThread6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        TestThread7.start();

        try {
            TestThread7.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestThread8.start();
        try {
            TestThread8.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestThread9.start();
        try {
            TestThread9.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        TestThread10.start();
    }

    /**
     * Méthode appelée par défaut, le point d'entrée de la classe
     *
     * @param args Tableau de Strings
     */
    public static void main(String[] args) {
        System.out.println("Lancement des threads!");
        firstThreadExecution();
        System.out.println("Pourquoi en relançant les mêmes threads j'ai un résultat différent : \n");
        firstThreadExecution();
        System.out.println("Parce qu'il y a de la concurrence entre les Threads !");
        secondthreadExecution();
    }
}
