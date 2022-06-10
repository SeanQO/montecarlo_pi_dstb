import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;  
import java.util.Map;  
import java.util.Queue;  
import java.util.concurrent.ConcurrentHashMap;  
import java.util.concurrent.ConcurrentLinkedQueue;  
  
public class Master implements Runnable{   
    //Subtask Processing Result Set  
    protected WorkerManager manager;
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
	
    public Master(long points) {  
        this.points = points;
    }  
      
    //Are all subtasks introduced?  
//    public boolean isComplete() {  
//        for(Map.Entry<String, Thread> entry : threadMap.entrySet()) {  
//            if(entry.getValue().getState() != Thread.State.TERMINATED)  
//                //Threads exist for completion  
//                return false;  
//        }  
//        return true;  
//    }  
      
  //Submit a subtask  
    private BigDecimal calcPi() {  
        //todo  
    	return new BigDecimal(0.0);
    }  
    
    // print the results to the console
 	private void print(int n) {
 		System.out.println("Calculation with: " + n);
 		System.out.println("Points in: " + this.manager.getIn() + ", points out: " + this.manager.getOut());
 		System.out.println("Pi calculated: " + this.calcPi() + ", In+out: " + (this.manager.getOut() + this.manager.getIn()));
 		System.out.println(
 				"Difference: Pi calculated (" + this.calcPi() + ") - Pi (" + this.pi.setScale(10, RoundingMode.CEILING)
 						+ "): " + this.calcPi().subtract(this.manager.getPi()).abs().setScale(10, RoundingMode.CEILING));
 		System.out.println("--------------- " + "\n");
 	}
      
    @Override
	public void run() {
    	while(true) {
			long task = points - calcPoints;
			if(task == 0) {
				long[] pointsCalc = this.manager.getWorker().resolveTask();
				this.in = pointsCalc[0];
				this.out = pointsCalc[1];
				
				// calculate current PI
				this.piCalc = this.calcPi();
			}
			
    	}
	}  
    

    //Returns the subtask result set  
//    public Map<String, Object> getResultMap() {  
//        return resultMap;  
//    }  
      
    //Execute all Worker processes and process them  
//    public void execute() {  
//        for(Map.Entry<String, Thread> entry : threadMap.entrySet()) {  
//            entry.getValue().start();  
//        }  
//    }
}  
