import java.io.*;

public class Client {

    private long points;

    public Client(){};

    public long menu() {
        boolean exit = false;
        System.out.println("*************************");
        System.out.println("*       WELLCOME        *");
        System.out.println("*************************");
        do {
            try {
                InputStreamReader r = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(r);
                System.out.println(" ");
                System.out.println("Enter number of points to calculate pi:");
                System.out.println(" ");
                this.points = Long.valueOf(br.readLine());
                exit = true;
            } catch (Exception e) {
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
                exit = false;
            }

        } while (!exit);

        return this.points;
    }
}
