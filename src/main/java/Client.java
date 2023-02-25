import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 8989;
        Scanner scanner = new Scanner(System.in);

        // 1. ask user for purchase
        while (true) {

            System.out.println("Enter purchase or type `end`");
            String line = scanner.nextLine();
            if ("end".equals(line)) {
                //clientLog.exportAsCSV(new File("log.scv"));
                System.out.println("Программа завершена!");
                break;
            }
            // 2. parse user request
            String[] userPurchase = line.split(" ");
            if (userPurchase.length > 2) {
                String title = userPurchase[0];
                String date = userPurchase[1];
                Integer sum = Integer.parseInt(userPurchase[2]);
                // 3. create json
                Purchase purchase = new Purchase(title, date, sum);
                String jsonString = purchase.toJson();

                // 4. send request to server
                try (
                        Socket clientSocket = new Socket(hostName, port);
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                )
                {
                    out.println(jsonString);
                }
            }
        }
    }
}