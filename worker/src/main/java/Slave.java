import java.util.Random;

import com.zeroc.Ice.Current;

import intfc.Worker;

public class Slave implements Worker{     
	
	boolean working;
	Random rd;
	public Slave() {
		working = false;
	}
//    @Override  
//    public void run() {  
//        while(true) {  
//            //Getting subtasks  
//            Task input = workQueue.poll(); 
//            if(input == null) break;
//            input = update(input);
//            //Write the processing results to the result set  
//            //resultMap.put(Integer.toString(input.hashCode()));  
//        }  
//    } 

    // get random value
	private double getRand() {
		return rd.nextDouble();
	}

    private void initRand(Long seed){
		this.rd = new Random();
        this.rd.setSeed(seed);
    }

  //calculate random points
	public long[] taskResolver(long l, Long seed) {
        int in = 0;
        int out = 0;
        working = true;

        for (long i = 0; i < l; i++) {
        	
            double x = this.getRand();
            double y = this.getRand();
            if (x * x + y * y <= 1) {
                // point is inside the circle
                in = in++;
            } else {
                // point is outside the circle
                out = out++;
            }
        }
        
        long[] inOut = {in, out};
        working = false;
        return inOut;
	}

	@Override
	public boolean callback(Current current) {
		if(working == false) {
			System.out.println("Slave ready");
			return true;
		}else {
			
			return false;
		}
	}

    @Override
    public long[] resolveTask(long l, long seed, Current current) {
        this.initRand(seed);
        
        int in = 0;
        int out = 0;
        working = true;
       
        for (long i = 0; i < l; i++) {
        
            double x = this.getRand();
            double y = this.getRand();
            if (x * x + y * y <= 1) {
                // point is inside the circle
                in++;
            } else {
                // point is outside the circle
                out++;
            }
        }
        
        long[] inOut = {in, out};
        working = false;
        return inOut;
    }

}  
