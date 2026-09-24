package fr.ldnr.ex3threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
/**
 *
 */
/**
 *
 */

/**
 *
 */
public class ThreadTime {

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Thread thread = new Thread(new MonRunnable(1000));

        System.out.println(df.format(new Date()));

        thread.start();
    }

    /**
     * Classe implémente l'interface Runnable
     *
     */
    private static class MonRunnable implements Runnable {

        private long delai;

        /**
         *
         * @param delai traduit le temps en millisecondes
         */
        public MonRunnable(long delai) {
            this.delai = delai;
        }


        @Override
        public void run() {
            System.out.println("Début du processus");
            int i = 1;
            while (i < 6) {
                try {
                    Thread.sleep(delai);
                    System.out.println(i + "-".repeat(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            System.out.println("Fin du processus");
        }
    }
}