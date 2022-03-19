package classes;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class Main {
    static String user = "root";
    static String Password = "";
    static Connection connection = MysqlConnection.connect();

    public static void main(String[] args) {
        ServerSocket serverSocket;

        Socket socket;
        try {
            serverSocket = new ServerSocket(6963);
            System.out.println("Server Started");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("client connected");
                Thread t = new Thread(new HandleClientRequest(socket));
                t.start();

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
