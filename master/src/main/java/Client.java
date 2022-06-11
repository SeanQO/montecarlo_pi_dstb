import java.io.*;

public class Client {

    private long points;
    private InputStreamReader r;
    private BufferedReader br;
    private long seed;

    public Client() {
        r = new InputStreamReader(System.in);
        br = new BufferedReader(r);
    };

    public void welcome() {
        System.out.println("*************************");
        System.out.println("*       WELLCOME        *");
        System.out.println("*************************");
    }

    private void inputError() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*************************");
        System.out.println("*      INPUT ERROR      *");
        System.out.println("*************************");
        System.out.println(" ");
        System.out.println("Verify entered number format.");
        System.out.println(" ");
        System.out.println("*************************");
        System.out.println(" ");
    }

    public long[] menu() {
        boolean exit = false;
        do {
            try {

                System.out.println(" ");
                System.out.println("*************************");
                System.out.println("Enter number of points to calculate pi:");
                System.out.println(" ");
                this.points = Long.valueOf(br.readLine());
                System.out.println("*************************");
                System.out.println("Enter random seed:");
                System.out.println(" ");
                this.seed = Long.valueOf(br.readLine());
                System.out.println("*************************");
                System.out.println(" ");
                exit = true;
            } catch (Exception e) {
                this.inputError();
                exit = false;
            }

        } while (!exit);

        long[] data = {points,seed};

        return data;
    }

    public void waitingForWorkers() {
        try {
            System.out.println(" ");
            System.out.println("Waiting for workers - (Press any key to run server logic)");
            System.out.println(" ");
            br.readLine();
            System.out.println("*************************");
            System.out.println(" ");
        } catch (Exception e) {
            this.inputError();
        }
    }

    public void waitingForNewWorkers(int currentWorkers) {
        try {
            System.out.println(" ");
            System.out.println("Number of workers in server: " + currentWorkers);
            System.out.println("Waiting for new workers - Press any key to run with previously added workers");
            System.out.println(" ");
            br.readLine();
            System.out.println("*************************");
            System.out.println(" ");
        } catch (Exception e) {
            this.inputError();
        }
    }

    public boolean shoulExit() {
        boolean exit = false;
        boolean realExit = true;
        do {
            System.out.println(" ");
            System.out.println("*************************");
            System.out.println("1. Enter new points to calculate.");
            System.out.println("2. Exit.");
            System.out.println("*************************");
            System.out.println(" ");
            try {
                int opt = Integer.valueOf(br.readLine());
                if (opt == 1) {
                    exit = true;
                    realExit = false;
                } else if (opt == 2){
                    exit = true;
                    realExit = true;
                }
                System.out.println("*************************");
                System.out.println(" ");
            } catch (Exception e) {
                this.inputError();
                exit = false;
            }
        } while (!exit);
        return realExit;
    }

    public void waitingForManualShutDown(){
        System.out.println("*************************");
        System.out.println("Server Logic disconected.");
        System.out.println("Waiting for manual shutdown. --> enter: Ctrl+c");
        System.out.println("*************************");
    }
}
