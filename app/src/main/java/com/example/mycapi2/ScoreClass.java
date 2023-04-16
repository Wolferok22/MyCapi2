package com.example.mycapi2;

public class ScoreClass extends Thread{

    private static int score = 0;

    private static boolean running = false;


    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        ScoreClass.score = score;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        ScoreClass.running = running;
    }

    public static void startCountScore(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running){
                    score++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();

    }
}
