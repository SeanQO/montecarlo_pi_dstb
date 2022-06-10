public class Master {
    private static Client c;
    private static MasterController controller;
    private static GPoints thread;
    

	public static void main(String[] args) {
        c = new Client();
        long points = c.menu();
        System.out.println("ENTERED POINTS --> " + points);

        thread = new GPoints(points);
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "master.config"))
        {
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> communicator.destroy()));

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Callback.Server");
            adapter.add(new WorkerManager(), com.zeroc.Ice.Util.stringToIdentity("server"));
            adapter.activate();

            thread.getInOut();

            communicator.waitForShutdown();
            
        }

	}
}
