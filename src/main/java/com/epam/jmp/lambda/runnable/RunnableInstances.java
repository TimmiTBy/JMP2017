package com.epam.jmp.lambda.runnable;

/**
 * Created by Ales on 05.06.2017.
 */
public class RunnableInstances {

    public static void main(String[] args) {
        //first
        Runnable firstrTask = () -> {
            new TaskSynchronizer().synchronize();
            try {
                Thread.sleep(1000);
                System.out.println("It was synchronized");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executeTask(firstrTask);

        //second
        executeTask(() -> {
            new TimeUpdater().updateTime();
            try {
                Thread.sleep(10000);
                System.out.println("Time was updated");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



    }

    public static void executeTask(Runnable task) {
       new Thread(task).start();
    }
}
