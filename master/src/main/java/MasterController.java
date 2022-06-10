import java.math.BigDecimal;
import java.math.RoundingMode;
  
public class MasterController implements Runnable{   
    //Subtask Processing Result Set  
    protected WorkerManager manager;

	private Master main;

    public MasterController() {  
        
    }  
    
    @Override
	public void run() {
    	while(true) {
			long task = manager.;
			if(task == 0) {
				// calculate current PI
				this.piCalc = main.calcPi();
				break;
			}else {
				long[] pointsCalc = this.manager.getWorker().resolveTask(task);
				//this.setIn(pointsCalc[0]);
				//this.setOut(pointsCalc[1]);
			}
    	}

	}

	public void excecutable(long task){
		
	}
}  
