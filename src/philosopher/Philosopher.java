package philosopher;

import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    private final String name;
    private final Fork left;
    private final Fork right;

    private int foodCount;
    private boolean isFull;

    private final CountDownLatch countDownLatch;

    public Philosopher(String name, Fork left, Fork right, CountDownLatch countDownLatch) {
        isFull = false;
        this.name = name;
        this.left = left;
        this.right = right;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public synchronized void run() {
        while (!isFull) {
            try {
                eat();
                reflection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " поел три раза. Философ устал. Философ отдыхает!!!");
        countDownLatch.countDown();

    }

    public synchronized void eat() throws InterruptedException {
        if (!left.isForkIsBlocked() && !right.isForkIsBlocked()) {
            startEat();
            String leftFork = left.getForkName();
            String rightFork = right.getForkName();
            System.out.println(this.name + " взял вилки " + leftFork + " и " +rightFork + " и приступил к обеду!");
            int timeToEat = 2000;
            Thread.sleep(timeToEat);
            foodCount++;
            int maxNumberOfSnacks = 3;
            if (foodCount >= maxNumberOfSnacks) {
                isFull = true;
            }
            putForks();
        }
    }

    private synchronized void startEat() {
        this.left.start();
        this.right.start();
    }

    public synchronized void reflection() throws InterruptedException {
        System.out.println(name + " размышляет о высоком");
        int timeToReflection = 1000;
        Thread.sleep(timeToReflection);
    }

    public synchronized boolean takeForks() {
        if (left.isForkIsBlocked()) {
            return false;
        }
        if (!right.isForkIsBlocked()) {
            return false;
        }
        foodCount++;
        System.out.printf("%s взял %s, %s и приступил %s к обеду. Кушает спагетти. \n", name, left, right, foodCount);
        return true;

    }

    public synchronized void putForks() {
        System.out.printf("%s Закончил кушать и положил вилки  %s и %s на стол. \n", name, left, right);
        left.setForkIsBlocked(false);
        right.setForkIsBlocked(false);
    }
}

