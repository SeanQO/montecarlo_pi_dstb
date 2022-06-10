import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Value;

import intfc.Server;

import intfc.WorkerPrx;

public class WorkerManager implements Server{
	
	private Master controllerMaster;
	private Queue<Slave> workers;
	
	
    public Slave getWorker() {
    	Slave w;
    	
    	if(workers.isEmpty()) {
    		w =  null;
    	}else {
    		w = workers.poll();
    		workers.add(w);
    	}
    	
    	return w;
    	
    }

	@Override
	public void callCallBack(WorkerPrx proxy, Current current) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void attach(WorkerPrx workerprx, Current current) {
		Slave w = getWorker(); 
		if(w.callback(current)) {
			this.workers.add((Slave) workerprx);	
			System.out.println("Worker Online");
		}
		
	}


	@Override
	public void detach(Value workerprx, Current current) {
		Slave w = getWorker();
		if(!w.callback(current)) {
			this.workers.remove(workerprx);
			System.out.println("Worker Offline");
		}
		
	}





    
    
}
