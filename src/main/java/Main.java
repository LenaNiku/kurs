import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        File categoriesFile = new File("categories.tsv");
        MaxCategory maxCategory = new MaxCategory(categoriesFile);
        Purchases purchases = new Purchases();

        int port = 8989;

        try (ServerSocket serverSocket = new ServerSocket(port);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    // 1. Read request from client
                    final String messageFromClient = in.readLine();

                    // 2. process request
                    Purchase purchase = Server.processRequest(messageFromClient);
                    purchases.addPurchase(purchase);

                    System.out.println(String.format("Message: %s, port: %d", messageFromClient, socket.getPort()));
                    // 3. Create maxCategory object (use some pattern)
                    MaxPurchase maxPurchase = maxCategory.getMaxPurchase(purchases);
                    // 4. Send response to client
                    out.println(maxPurchase.toJson());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }

}

