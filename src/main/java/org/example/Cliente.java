package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket clientSocket;
        Scanner scanner = new Scanner(System.in);
        try {
            clientSocket = new Socket("localhost", 25565);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (!clientSocket.isClosed()){
                System.out.println(in.readLine());
                String id = scanner.nextLine();
                out.println(id);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
