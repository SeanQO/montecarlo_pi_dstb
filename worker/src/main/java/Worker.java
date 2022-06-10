import java.util.Random; 

public class Worker{        
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
		Random rd = new Random();
		return rd.nextDouble();
	}

    //calculate random points
    public long[] resolveTask(long n){
    	
        int in = 0;
        int out = 0;

        for (long i = 0; i < n; i++) {
        	
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
        return inOut;
    }
}  