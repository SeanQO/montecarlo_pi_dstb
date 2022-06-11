import java.util.Random;

public class TPoints implements Runnable {

    private Slave boss;
    Random rd;
    long p;
    long seed;

    public TPoints(Slave boss, long l, long seed) {
        this.boss = boss;
        this.p = l;
        this.seed = seed;
    }

    // get random value
    private double getRand() {
        return rd.nextDouble();
    }

    private void initRand(Long seed) {
        this.rd = new Random();
        this.rd.setSeed(seed);
    }

    @Override
    public void run() {
        this.initRand(seed);

        int in = 0;
        int out = 0;

        for (long i = 0; i < p; i++) {

            double x = this.getRand();
            double y = this.getRand();
            if (x * x + y * y <= 1) {
                // point is inside the circle
                in++;
            } else {
                // point is outside the circle
                out++;
            }
        }

        long[] inOut = { in, out };
        boss.pointsReady(inOut);
    }

}
