import intfc.*;


public class Main_Worker {
	
	Master m;

	public static void main(String[] args) {
		

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.client"))
        {
            
            intfc.ServerPrx serverPrx = ServerPrx.checkedCast(
            communicator.propertyToProxy("Server.Proxy")).ice_twoway().ice_timeout(-1).ice_secure(false);
            if(serverPrx == null)
            {
                System.err.println("invalid proxy");
            }
            
            serverPrx.

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Worker.Server");
            adapter.add(new Slave(), com.zeroc.Ice.Util.stringToIdentity("cliente"));
            adapter.activate();
            
            WorkerPrx workerPrx = WorkerPrx.uncheckedCast(adapter.createProxy(
            com.zeroc.Ice.Util.stringToIdentity("cliente")));


            serverPrx.callCallBack(workerPrx);
            
        }

	}

}
