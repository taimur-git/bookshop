package com.bookshop.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileSenderController {
    final File[] fileToSend = new File[1];
    public Button sendFile;
    public Button chooseFile;
    public Label fileName;

    public void sendFileAction(ActionEvent actionEvent) {

        if (fileToSend[0] == null) {
            fileName.setText("Please choose a file to send first!");
            // If a file has been selected then do the following.
        } else {
            try {
                // Create an input stream into the file you want to send.
                FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                // Create a socket connection to connect with the server.
                Socket socket = new Socket("localhost", 1234);
                // Create an output stream to write to the server over the socket connection.
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                // Get the name of the file you want to send and store it in filename.
                String fileName = fileToSend[0].getName();
                // Convert the name of the file into an array of bytes to be sent to the server.
                byte[] fileNameBytes = fileName.getBytes();
                // Create a byte array the size of the file so don't send too little or too much data to the server.
                byte[] fileBytes = new byte[(int)fileToSend[0].length()];
                // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server.
                fileInputStream.read(fileBytes);
                // Send the length of the name of the file so server knows when to stop reading.
                dataOutputStream.writeInt(fileNameBytes.length);
                // Send the file name.
                dataOutputStream.write(fileNameBytes);
                // Send the length of the byte array so the server knows when to stop reading.
                dataOutputStream.writeInt(fileBytes.length);
                // Send the actual file.
                dataOutputStream.write(fileBytes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }

    public void chooseFileAction(ActionEvent actionEvent) {
        // Create a file chooser to open the dialog to choose a file.
        FileChooser fileChooser = new FileChooser();
        // Set the title of the dialog.
        fileChooser.setTitle("Choose a file to send.");
        String initialDirectoryPath = "input";
        File initialDirectory = new File(initialDirectoryPath);
        fileChooser.setInitialDirectory(initialDirectory);
        // Show the dialog and if a file is chosen from the file chooser execute the following statements.
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Get the selected file.
            //File fileToSend = selectedFile;
            fileToSend[0] = selectedFile;
            // Change the text of a JavaFX label to have the file name.
            fileName.setText("The file you want to send is: " + selectedFile.getName());
        }
    }
}
