package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(4000)){
            System.out.println("Server Started");

            while(true){
                try (Connector connector = new Connector(server)){
                    String line = connector.readLine();
                    System.out.println("Получено: " + line);

                    String result = analysis(line);
                    System.out.println("Отправлено: " + result);
                    connector.writeLine(result);
                    connector.close();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String analysis(String line){
        System.out.println("line-" + line);
        String sentence = line.trim();
        System.out.println("str-" + sentence);
        if (sentence.equals("")){
            return "Words not found\r\n";
        }

        HashMap<String, Integer> map = new HashMap<>();
        sentence = sentence.toLowerCase();

        for(String word : sentence.split("\\W+")) {
            if(word.isEmpty()) {
                continue;
            }
            if(map.containsKey(word)) {
                map.put(word, map.get(word)+1);
            }
            else {
                map.put(word, 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }

        return result.toString();
    }
}
