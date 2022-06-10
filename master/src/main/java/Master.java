import java.math.BigDecimal;
import java.math.RoundingMode;

public class Master {
    private static Client c;
    private static MasterController controller;

    //Divide points constraints
    public final static long PWR1TO4 = 2;
    public final static long PWR5TO6 = 4;
    public final static long PWR7 = 5;
    public final static long PWR8 = 8;

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

    private long task;

	public static void main(String[] args) {
        c = new Client();
        long points = c.menu();
        System.out.println("ENTERED POINTS --> " + points);
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "master.config"))
        {
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> communicator.destroy()));

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Callback.Server");
            adapter.add(new WorkerManager(), com.zeroc.Ice.Util.stringToIdentity("server"));
            adapter.activate();



            communicator.waitForShutdown();
            
        }

	}

    public BigDecimal calcPi() {  
        //todo  
    	return new BigDecimal(0.0);
    }  
    
    // print the results to the console
 	private void print(int n) {
 		System.out.println("Calculation with: " + n);
 		System.out.println("Points in: " + this.getIn() + ", points out: " + this.getOut());
 		System.out.println("Pi calculated: " + this.calcPi() + ", In+out: " + (this.getOut() + this.getIn()));
 		System.out.println(
 				"Difference: Pi calculated (" + this.calcPi() + ") - Pi (" + this.pi.setScale(10, RoundingMode.CEILING)
 						+ "): " + this.calcPi().subtract(this.pi).abs().setScale(10, RoundingMode.CEILING));
 		System.out.println("--------------- " + "\n");
 	}

     public long getIn() {
		return in;
	}

	public void setIn(long in) {
		this.in = in;
	}

	public long getOut() {
		return out;
	}

	public void setOut(long out) {
		this.out = out;
	}

    private void setTask(long t){
        switch(){
            case 1:
            break;
        }
        this.task = po
    }
}
