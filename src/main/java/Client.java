import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 8989;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter purchase (title date sum) or type `end`");
            String line = scanner.nextLine();
            if ("end".equals(line)) {
                System.out.println("Программа завершена!");
                break;
            }

            String[] userPurchase = line.split(" ");
            if (userPurchase.length > 2) {
                String title = userPurchase[0];
                String date = userPurchase[1];
                Integer sum = Integer.parseInt(userPurchase[2]);
                Purchase purchase = new Purchase(title, date, sum);
                String jsonString = purchase.toJson();

                try (
                        Socket clientSocket = new Socket(hostName, port);
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                )
                {
                    out.println(jsonString);
                    final String messageFromServer = in.readLine();
                    System.out.printf("Message from server: %s, port: %d%n",
                            messageFromServer, clientSocket.getPort());
                }
            }
        }
    }
}