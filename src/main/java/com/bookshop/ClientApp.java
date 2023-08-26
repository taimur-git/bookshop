package com.bookshop;

import com.bookshop.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp extends Application {
    @Override
    public void start(Stage stage){
        Model.getInstance().getViewsFactory().showLoginWindow();

        Thread clientThread = new Thread(new ClientThread());
        clientThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ClientThread implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 8888);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Server: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}