import java.util.ArrayList;
import java.util.List;
import java.util.Queue;  

public class WorkerManager {
	
	private Master controllerMaster;
	private Queue<Worker> workers;
	
	public void attach(Worker worker) {
        this.workers.add(worker);
    }

    public void detach(Worker worker) {
        this.workers.remove(worker);
    }

    public Worker getWorker() {
    	Worker w;
    	
    	if(workers.isEmpty()) {
    		w =  null;
    	}else {
    		w = workers.poll();
    		workers.add(w);
    	}
    	
    	return w;
    	
    }
}
