import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.zeroc.Ice.Current;

import intfc.Worker;

public class Slave implements Worker {

    boolean working;
    long[] arrP;
    private Semaphore sem = new Semaphore(1);

    public Slave() {
        working = false;
    }
    // @Override
    // public void run() {
    // while(true) {
    // //Getting subtasks
    // Task input = workQueue.poll();
    // if(input == null) break;
    // input = update(input);
    // //Write the processing results to the result set
    // //resultMap.put(Integer.toString(input.hashCode()));
    // }
    // }

    @Override
    public boolean callback(Current current) {
        if (working == false) {
            System.out.println("Slave ready");
            return true;
        } else {

            return false;
        }
    }

    @Override
    public long[] resolveTask(long l, long seed, Current current) {
        long still = l;
        ExecutorService exe = Executors.newFixedThreadPool(10);

        while (still > 0) {
            exe.execute(new TPoints(this, l, seed));
            System.out.println("------");
            seed++;
            long task = arrP[0] + arrP[1];
            still -= task;
        }

        exe.shutdown();
        while (!exe.isTerminated())
            ;

        return arrP;
    }

    public void pointsReady(long[] inOut) {
        try {
            sem.acquire();
            this.arrP = inOut;
            sem.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
