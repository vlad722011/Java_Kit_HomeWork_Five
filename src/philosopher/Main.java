package philosopher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    /*
    1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит
    тарелка спагетти.
    2. Вилки лежат на столе между каждой парой ближайших философов.
    3. Каждый философ может либо есть, либо размышлять.
    4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
    5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)


    Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
     */


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        Fork fork1 = new Fork("1");
        Fork fork2 = new Fork("2");
        Fork fork3 = new Fork("3");
        Fork fork4 = new Fork("4");
        Fork fork5 = new Fork("5");

        List<Philosopher> roundTable = new ArrayList<>();

        roundTable.add(new Philosopher("Философ №1", fork1, fork2, countDownLatch));
        roundTable.add(new Philosopher("Философ №2", fork2, fork3, countDownLatch));
        roundTable.add(new Philosopher("Философ №3", fork3, fork4, countDownLatch));
        roundTable.add(new Philosopher("Философ №4", fork4, fork5, countDownLatch));
        roundTable.add(new Philosopher("Философ №5", fork5, fork1, countDownLatch));

        System.out.println("Старт программы.");
        System.out.println("______________________________________________________________________");

        for (Philosopher philosopher : roundTable) {
            philosopher.start();
        }

        while (countDownLatch.getCount() != 0) {
            Thread.sleep(1000);
        }

        System.out.println("Все философы наелись и устали. Программа завершена!.");
        System.out.println("______________________________________________________________________");
    }
}

