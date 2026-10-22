package Basics_01;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server is waiting for client...");

        Socket s = ss.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
                s.getOutputStream(), true);

        BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in));

        String msg;

        while (true) {
            System.out.print("Server: ");
            msg = keyboard.readLine();

            out.println(msg);

            if (msg.equalsIgnoreCase("exit"))
                break;

            msg = in.readLine();
            System.out.println("Client: " + msg);

            if (msg.equalsIgnoreCase("exit"))
                break;
        }

        s.close();
        ss.close();
    }
}