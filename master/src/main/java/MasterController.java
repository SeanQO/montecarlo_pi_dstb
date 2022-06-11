import java.util.Random;

import com.zeroc.Ice.Current;

import intfc.Worker;
import intfc.WorkerPrx;

public class MasterController implements Runnable {
	// Subtask Processing Result Set
	protected WorkerManager manager;

	private GPoints main;

	private Long maxDot;

	private Long seed;

	private Random r;

	public MasterController(Long maxDot, GPoints main, long seed, WorkerManager wm) {
		this.main = main;
		this.maxDot = maxDot;
		this.seed = seed;
		this.r = new Random(this.seed);
		this.manager = wm;
	}

	public MasterController(Long maxDot, GPoints main) {
		this.main = main;
		this.maxDot = maxDot;
		this.r = new Random();
	}

	@Override
	public void run() {
		// do while dots = max dots
		// Current c = new Current();
		long[] inOut = manager.getWorker().resolveTask(maxDot, seed);
		// System.out.println("Puntos :" + inOut[0] + " " +inOut[1]);
		// main.setIn(inOut[0]);
		// main.setOut(inOut[1]);
		// System.out.println("PUntos :" + inOut[0] + " " +inOut[1]);
		main.pointsReady(inOut);

	}

	public long[] taskResolver(WorkerPrx workerPrx, Current current, Long l) {

		return workerPrx.resolveTask(l, seed);
	}

}
