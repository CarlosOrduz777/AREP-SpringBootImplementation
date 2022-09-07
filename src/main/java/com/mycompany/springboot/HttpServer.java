package com.mycompany.springboot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        SpringBoot.main(args);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean firstLine = true;
            String path = "";
            while ((inputLine = in.readLine()) != null) {
                if(firstLine){
                    String[] receive = inputLine.split(" ");
                    path = receive[1];
                }
                firstLine = false;
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {break; }
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                   + getService(path);
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    public static String getService(String path) throws InvocationTargetException, IllegalAccessException {
        String output ="";
        if(Data.apisDictionary.containsKey(path)){
            Method currentService = Data.apisDictionary.get(path);
            output = (String) currentService.invoke(null);
        }
        return output;
    }

    public static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}