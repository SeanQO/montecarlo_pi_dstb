import java.util.Random;
  
public class MasterController implements Runnable{   
    //Subtask Processing Result Set  
    protected WorkerManager manager;

	private Master main;

	private Long maxDot;

	private Long seed;

	private Random r;

	private int in;
	private int out;

	public MasterController(Long maxDot, Master main, long seed) {
		this.main = main;
		this.maxDot = maxDot;
		this.seed = seed;
		this.r = new Random(this.seed);
    }

	public MasterController(Long maxDot, Master main) {
		this.main = main;
		this.maxDot = maxDot;
		this.r = new Random();
    }
    
    @Override
	public void run() {
		//do while dots = max dots
    	for (int i = 0; i < maxDot; i++) {
			double x = this.getRand();
			double y = this.getRand();
			if (x * x + y * y <= 1) {
				// point is inside the circle
				this.in++;
			} else {
				// point is outside the circle
				this.out++;
			}

		}

		main.setIn(in);
		main.setOut(out);

	}

	private double getRand() {
		return r.nextDouble();
	}

}  
