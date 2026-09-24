package fr.ldnr.ex3threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 *
 */

public class ThreadTime {

    public static void main(String[] args) {
        
        Thread thread = new Thread(new MonRunnable(1000));
        long epochMillis = Instant.now().toEpochMilli();

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
            System.out.println("(L'horloge s'arrête après une minute !)\n");
            int i = 1;
            long epochMillis = Instant.now().toEpochMilli();
            while (i < 60) {
                try {
                    Thread.sleep(delai);
                    epochMillis +=  1000L;
                    Date date = new Date(epochMillis);
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    System.out.print("\rHorloge: " + df.format(date));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            System.out.println();
        }
    }
}