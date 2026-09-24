package fr.ldnr.ex3threads;

public class TestThread extends Thread {
    private String typeAction;
    private int firstThreadNumber;
    private int secondThreadNumber;
    private static String message;

    public void setFirstThreadNumber(int firstThreadNumber) {
        this.firstThreadNumber = firstThreadNumber;
    }

        public void setSecondThreadNumber(int secondThreadNumber) {
        this.secondThreadNumber = secondThreadNumber;
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
                break;
            case "second":
                callSecond();
                break;
        }
    }

    private void callFirst() {
        for (int i = 0; i < 5; i++) {
            System.err.print(repeteMotiv(returnFirstMotiv(this.firstThreadNumber), i));
        }
        System.out.println();
    }

    private void callSecond() {
        String[] beginEnd = {"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*"};
        message += returnSecondMotiv(beginEnd[this.secondThreadNumber - 1], this.secondThreadNumber) +"\n";
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
        testThread1.setFirstThreadNumber(1);
        TestThread testThread2 = new TestThread("first");
        testThread2.setFirstThreadNumber(2);
        TestThread testThread3 = new TestThread("first");
        testThread3.setFirstThreadNumber(3);
        TestThread testThread4 = new TestThread("first");
        testThread4.setFirstThreadNumber(4);
        TestThread testThread5 = new TestThread("first");
        testThread5.setFirstThreadNumber(5);

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

        testThread5.join();
        testThread4.join();
        testThread3.join();
        testThread2.join();
        testThread1.join();
    }

    /**
     * Méthode pour répondre à la quesion
     */
    public static void secondthreadExecution() throws InterruptedException {
        message = "";
        TestThread secondTestThread1 = new TestThread("second");
        secondTestThread1.setSecondThreadNumber(1);
        TestThread secondTestThread2 = new TestThread("second");
        secondTestThread2.setSecondThreadNumber(2);
        TestThread secondTestThread3 = new TestThread("second");
        secondTestThread3.setSecondThreadNumber(3);
        TestThread secondTestThread4 = new TestThread("second");
        secondTestThread4.setSecondThreadNumber(4);
        TestThread secondTestThread5 = new TestThread("second");
        secondTestThread5.setSecondThreadNumber(5);
        TestThread secondTestThread6 = new TestThread("second");
        secondTestThread6.setSecondThreadNumber(6);
        TestThread secondTestThread7 = new TestThread("second");
        secondTestThread7.setSecondThreadNumber(7);
        TestThread secondTestThread8 = new TestThread("second");
        secondTestThread8.setSecondThreadNumber(8);
        TestThread secondTestThread9 = new TestThread("second");
        secondTestThread9.setSecondThreadNumber(9);
        TestThread secondTestThread10 = new TestThread("second");
        secondTestThread10.setSecondThreadNumber(10);

        secondTestThread1.start();

        try {
            secondTestThread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        secondTestThread2.start();

        try {
            secondTestThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        secondTestThread3.start();

        try {
            secondTestThread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        secondTestThread4.start();
        try {
            secondTestThread4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        secondTestThread5.start();

        try {
            secondTestThread5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        secondTestThread6.start();

        try {
            secondTestThread6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        secondTestThread7.start();

        try {
            secondTestThread7.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        secondTestThread8.start();

        try {
            secondTestThread8.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        secondTestThread9.start();

        try {
            secondTestThread9.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        secondTestThread10.start();

        try {
            secondTestThread10.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.err.println(message);
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
            System.out.println("\n--- Exercice 3.1 ---\n");
            System.out.println("Pourquoi en relançant les mêmes threads j'ai un résultat différent ? : \n");
            System.out.println("Premier lancement : \n");
            firstThreadExecution();
            System.out.println("\n Second lancement : \n");
            firstThreadExecution();
            System.out.println("\nRéponse: il y a de la concurrence entre les Threads !");
            System.out.println("\n--- Exercice 3.2 ---\n");
            secondthreadExecution();
            System.out.println("\nLes Threads sont sérialisés avec \"NomThread.join()\".\nCe ne sont plus vraiment des threads mais des séquences !\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Une exception s'est produite : \n" + e.getMessage());
        }



    }
}
