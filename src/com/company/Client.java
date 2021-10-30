package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Connector connector = new Connector("127.0.0.1",4000)){
            System.out.println("Connected to server");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Input sentence: ");
            connector.writeLine(scanner.nextLine());

            System.out.println("Response:");
            String line;
            while ((line = connector.readLine()) != null){
                System.out.println(line);
            }
            connector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
