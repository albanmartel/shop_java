package fr.ldnr.ex3threads;

import java.awt.*;

public class TestThread extends Thread {
    private String typeAction;
    private int threadNumber;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    /**
     *
     * @param typeAction ce paramètre permet d'appeler différentes actions en fonction du nom de l'action
     */
    public TestThread(String typeAction) {
        this.typeAction = typeAction;
    }

    /**
     * Immplémentation de la méthode run() de l'interface TestThread
     */
    @Override
    public void run() {
        switch (typeAction) {
            case "first":
                callFirst();
            case "second":
                callSecond();
        }
    }

    private void callFirst() {
        String message = "";
        for (int i = 0; i < 10; i++) {
            message += repeteMotiv(returnFirstMotiv(this.threadNumber), 5) + "\n";
        }
        if (this.message != null){
            message += this.message;
        }
        this.message = message;
    }

    private void callSecond() {
        String message = "";
        String[] beginEnd = {"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*"};
        for (int i = 1; i < 11; i++) {
            message += returnSecondMotiv(beginEnd[i-1], i);
            message += "\n";
        }
        if (this.message != null){
            message += this.message;
        }
        this.message = message;
    }

        /**
     *
     * @param i indique l'indice du theard
     * @return un chaîne construite
     */
    public static String repeteMotiv(String motiv, int i) {
        String message = "";
        /* Méthode Java 11 */
        //return motiv.repeat(i);

        /* Méthode avant Java 11 */
        for (int j = 0; j < i; j++) {
            message +=  motiv;
        }
        return message;
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
    public static void firstThreadExecution() throws InterruptedException {
        TestThread testThread1 = new TestThread("first");
        testThread1.setThreadNumber(1);
        TestThread testThread2 = new TestThread("first");
        testThread2.setThreadNumber(2);
        TestThread testThread3 = new TestThread("first");
        testThread3.setThreadNumber(3);
        TestThread testThread4 = new TestThread("first");
        testThread4.setThreadNumber(4);
        TestThread testThread5 = new TestThread("first");
        testThread5.setThreadNumber(5);

        testThread5.start();
        testThread4.start();
        testThread3.start();
        testThread2.start();
        testThread1.start();
        /*
        Thread thirdThread = new Thread() {
            public void run() {
                System.out.println("!!! Autre écriture!!!");
            }
        };
        thirdThread.start();
        */
        /*
        testThread5.join();
        testThread4.join();
        testThread3.join();
        testThread2.join();
        testThread1.join();
        */

        System.out.println(testThread1.getMessage());
    }

    /**
     * Méthode pour répondre à la quesion
     */
    public static void secondthreadExecution() throws InterruptedException {
        TestThread testThread1 = new TestThread("second");
        TestThread testThread2 = new TestThread("second");
        TestThread testThread3 = new TestThread("second");
        TestThread testThread4 = new TestThread("second");
        TestThread testThread5 = new TestThread("second");
        TestThread testThread6 = new TestThread("second");
        TestThread testThread7 = new TestThread("second");
        TestThread testThread8 = new TestThread("second");
        TestThread testThread9 = new TestThread("second");
        TestThread testThread10 = new TestThread("second");

        testThread1.start();

        try {
            testThread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        testThread2.start();

        try {
            testThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        testThread3.start();

        try {
            testThread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        testThread4.start();
        try {
            testThread4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testThread5.start();

        try {
            testThread5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testThread6.start();

        try {
            testThread6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testThread7.start();

        try {
            testThread7.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testThread8.start();

        try {
            testThread8.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testThread9.start();

        try {
            testThread9.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        testThread10.start();

        try {
            testThread9.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(testThread1.getMessage());
    }

    /**
     * Méthode appelée par défaut, le point d'entrée de la classe
     *
     * @param args Tableau de Strings
     */
    public static void main(String[] args) {
        System.out.println("Lancement des threads!");
        TestThread testThread = new TestThread("first");
        try {
            System.out.println("Pourquoi en relançant les mêmes threads j'ai un résultat différent : \n");
            firstThreadExecution();
            testThread.setMessage("");
            System.out.println("Parce qu'il y a de la concurrence entre les Threads !");
            firstThreadExecution();
            testThread.setMessage("");
            secondthreadExecution();
            testThread.setMessage("");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Une exception s'est produite : \n" + e.getMessage());
        }



    }
}
