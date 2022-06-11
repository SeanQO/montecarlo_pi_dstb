import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.math.RoundingMode;

public class GPoints {
    // Total points
    protected long points;
    // calculated points
    protected long calcPoints;
    // calculation of pi
    private BigDecimal piCalc;
    // Real pi
    private BigDecimal pi = new BigDecimal(3.141592653589793238462643);
    // points inside circle
    private long in = 0;
    // points outside circle
    private long out = 0;
    // Max dots to randomize
    private final static long TASK = 10000000;
    // Seed to random
    private long seed;
    private WorkerManager wm;
    private Semaphore sem = new Semaphore(1);

    public GPoints(long p, WorkerManager wm, long seed) {
        this.points = p;
        this.wm = wm;
        this.seed = seed;
    }

    public BigDecimal calcPi() {
        BigDecimal piTmp = BigDecimal
                .valueOf(4.0 * ((double) this.getIn() / ((double) this.getIn() + (double) this.getOut())));
        return piTmp;
    }

    public void pointsReady(long[] inOut) {
        try {
            sem.acquire();
            // System.out.println(getIn() + " --- " + getOut());

            // System.out.println("antes if" + inOut[0] +" "+inOut[1]);
            this.in += inOut[0];
            this.out += inOut[1];
            // System.out.println(getIn() + " --- " + getOut());

            long currentPoints = getIn() + getOut();
            // System.out.println("cu"+ currentPoints);

            if (currentPoints == this.points) {
                System.out.println("en if");
                this.piCalc = calcPi();
                print();
            }
            sem.release();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    // print the results to the console
    private void print() {
        System.out.println("Calculation with: " + points);
        System.out.println("Points in: " + this.getIn() + ", points out: " + this.getOut());
        System.out.println("Pi calculated: " + this.calcPi() + ", In+out: " + (this.getOut() + this.getIn()));
        System.out.println(
                "Difference: Pi calculated (" + this.calcPi() + ") - Pi (" + this.pi.setScale(10, RoundingMode.CEILING)
                        + "): " + this.calcPi().subtract(this.pi).abs().setScale(10, RoundingMode.CEILING));
        System.out.println("--------------- " + "\n");
    }

    public long getIn() {
        return in;
    }

    public void setIn(long in) {
        this.in = in;
    }

    public long getOut() {
        return out;
    }

    public void setOut(long out) {
        this.out = out;
    }

    public void getInOut() {

        long still = points;
        // while (calcPoints < still) {
        // if (calcPoints != 0) {
        // still -= calcPoints;
        // }
        ExecutorService exe = Executors.newFixedThreadPool(10);
        while (still > 0) {
            long la = TASK;
            if (still < TASK) {
                la = still;
            }
            exe.execute(new MasterController(la, this, seed, wm));
            seed++;
            still -= TASK;
        }
        exe.shutdown();
        while (!exe.isTerminated())
            ;
        // }
    }
}
