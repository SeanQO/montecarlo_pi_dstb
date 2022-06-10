import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Value;

import intfc.Server;
import intfc.Subject;
import intfc.WorkerPrx;

public class WorkerManager implements Subject, Server{
	
	private Master controllerMaster;
	private Queue<Worker1> workers;
	
	
    public Worker1 getWorker() {
    	Worker1 w;
    	
    	if(workers.isEmpty()) {
    		w =  null;
    	}else {
    		w = workers.poll();
    		workers.add(w);
    	}
    	
    	return w;
    	
    }


	@Override
	public void sendPoints(WorkerPrx proxy, long r, Current current) {
		proxy.callback(r);
	}

	@Override
	public void attach(WorkerPrx workerprx, Current current) {
		this.workers.add((Worker1) workerprx);	
		
	}

	@Override
	public void detach(WorkerPrx workerprx, Current current) {
		this.workers.remove(workerprx);
		
	}


    
    
}
