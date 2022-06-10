import java.util.Random;

import com.zeroc.Ice.Current;

import intfc.Worker;
import intfc.WorkerPrx;
  
public class MasterController implements Runnable{   
    //Subtask Processing Result Set  
    protected WorkerManager manager;
	
	private GPoints main;

	private Long maxDot;

	private Long seed;

	private Random r;

	public MasterController(Long maxDot, GPoints main, long seed) {
		this.main = main;
		this.maxDot = maxDot;
		this.seed = seed;
		this.r = new Random(this.seed);
    }

	public MasterController(Long maxDot, GPoints main) {
		this.main = main;
		this.maxDot = maxDot;
		this.r = new Random();
    }
    
    @Override
	public void run() {
		//do while dots = max dots
		Current c = new Current();
		long[] inOut = taskResolver(manager.getWorker(), c, maxDot);
		main.setIn(inOut[0]);
		main.setOut(inOut[1]);

	}

	public long[] taskResolver(WorkerPrx workerPrx, Current current, Long l){

		return workerPrx.resolveTask(l);
	}

	private double getRand() {
		return r.nextDouble();
	}

}  
