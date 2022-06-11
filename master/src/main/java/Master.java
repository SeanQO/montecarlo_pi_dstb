public class Master {
    private static Client c;
    private static MasterController controller;
    private static GPoints thread;

    public static void main(String[] args) {
        boolean exit = false;
        c = new Client();
        c.welcome();
        boolean reRun = false; 
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "master.config")) {

            Runtime.getRuntime().addShutdownHook(new Thread(() -> communicator.destroy()));

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Callback.Server");
            WorkerManager wm = new WorkerManager();
            adapter.add(wm, com.zeroc.Ice.Util.stringToIdentity("server"));
            adapter.activate();
           
            do {
                if(!reRun){
                    c.waitingForWorkers();
                }else{
                    c.waitingForNewWorkers(wm.getNumOfWorkers());
                }
                
                long points = c.menu();

                thread = new GPoints(points, wm);
                thread.getInOut();
                exit = c.shoulExit();
                reRun = !exit;
            } while (!exit);

            c.waitingForManualShutDown();
            communicator.waitForShutdown();

        }

    }
}
