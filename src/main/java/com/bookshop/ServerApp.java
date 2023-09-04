package com.bookshop;

import com.bookshop.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp extends Application {
    @Override
    public void start(Stage stage){
        Model.getInstance().setAdminFlag(true);
        //Model.getInstance().getViewsFactory().showFileSenderPage();
        Model.getInstance().getViewsFactory().showStoreFrontPage();
        //Model.getInstance().getViewsFactory().showServerChatPage();
    }

}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Process input from client and send response
                String response = processInput(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processInput(String input) {
        // Process client input and generate response
        return "Server says: " + input;
    }
}
