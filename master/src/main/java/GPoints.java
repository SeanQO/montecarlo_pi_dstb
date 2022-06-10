import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.math.RoundingMode;

public class GPoints {
    //Total points
    protected long points;
    //calculated points
    protected long calcPoints;
    //calculation of pi
	private BigDecimal piCalc;
    //Real pi
	private BigDecimal pi = new BigDecimal(3.141592653589793238462643);
	//points inside circle
	private long in = 0;
	//points outside circle
	private long out = 0;
    //Max dots to randomize
    private final static long TASK = 10000000;
    //Seed to random
    private long seed = 1;
    
    public GPoints(long p){
        this.points = p;
    }

    public BigDecimal calcPi() {  
        BigDecimal piTmp = BigDecimal
				.valueOf(4.0 * ((double) this.getIn() / ((double) this.getIn() + (double) this.getOut())));
		return piTmp;
    }  

    public void pointsReady(long[] inOut){
        this.in += inOut[0];
        this.out += inOut[1];

        long currentPoints = getIn() + getOut();

        if (currentPoints == this.points) {
            this.piCalc = calcPi();
            print();
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

    public void getInOut(){
        while (calcPoints < points) {
            long still = 20;
            if (calcPoints != 0) {
                still -= calcPoints;
            }
            ExecutorService exe = Executors.newFixedThreadPool(10);
            while (still > 0) {
                exe.execute(new MasterController(TASK, this, seed));
                seed++;
                still -= TASK;
            }
            exe.shutdown();
            while (!exe.isTerminated());
        }
    }
}