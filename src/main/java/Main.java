import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        File categoriesFile = new File("categories.tsv");
        MaxCategory maxCategory = new MaxCategory(categoriesFile);
        Purchases purchases = new Purchases();

        int port = 8989;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    final String messageFromClient = in.readLine();

                    Purchase purchase = Server.processRequest(messageFromClient);
                    purchases.addPurchase(purchase);

                    System.out.printf("Message from client: %s, port: %d%n", messageFromClient, socket.getPort());

                    MaxPurchase maxPurchase = maxCategory.getMaxPurchase(purchases);

                    out.println(maxPurchase.toJson());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }

}

