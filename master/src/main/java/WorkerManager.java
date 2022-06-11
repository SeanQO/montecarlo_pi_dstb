import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Value;

import intfc.Server;

import intfc.WorkerPrx;

public class WorkerManager implements Server {

	private Master controllerMaster;
	private Queue<WorkerPrx> workers;

	public WorkerManager() {

		workers = new ArrayDeque<WorkerPrx>();
	}

	public WorkerPrx getWorker() {
		WorkerPrx w = null;
		boolean nuai = true;

		while (nuai) {
			if (workers.isEmpty()) {

				break;
			} else {

				w = workers.poll();
				workers.add(w);
				nuai = false;
				return w;
			}
		}
		return w;
	}

	@Override
	public void callCallBack(WorkerPrx proxy, Current current) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attach(WorkerPrx workerprx, Current current) {
		this.workers.add((WorkerPrx) workerprx);

		System.out.println("Worker Online" + workers.size());

	}

	@Override
	public void detach(WorkerPrx workerprx, Current current) {
		WorkerPrx w = getWorker();
		if (!w.callback()) {
			this.workers.remove(workerprx);
			System.out.println("Worker Offline");
		}
	}

	public int getNumOfWorkers() {
		return workers.size();
	}

}