package com.bookshop.Controllers;

import com.bookshop.Models.MyFile;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileReceiverController {
    static ArrayList<MyFile> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int fileId = 0;
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            try{
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                int fileSize = dataInputStream.readInt();
                //if file exists
                if(fileSize > 0) {
                    byte[] fileBytes = new byte[fileSize];

                    dataInputStream.readFully(fileBytes, 0, fileBytes.length);

                    String fileName = new String(fileBytes, 0, fileBytes.length);
                    int fileContentLength = dataInputStream.readInt();

                    byte[] fileContentBytes = new byte[0];
                    if (fileContentLength > 0) {
                        fileContentBytes = new byte[fileContentLength];
                        dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);

                        if (getFileExtension(fileName).equals("pdf")) {
                            String fileContent = new String(fileContentBytes, 0, fileContentBytes.length);
                            System.out.println("File content: " + fileContent);
                        }
                    }
                    MyFile myFile = new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName));
                    files.add(myFile);
                    System.out.println("File received: " + myFile.getName());
                    fileId++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String getFileExtension(String fileName){
        int index = fileName.lastIndexOf('.');
        if(index > 0){
            return fileName.substring(index + 1);
        }else{
            return "";
        }
    }
}

/*
class FileReceiverThread implements Runnable{
    private Socket socket;
    private int fileId;

    public FileReceiverThread(Socket socket, int fileId) {
        this.socket = socket;
        this.fileId = fileId;
    }

    public void run(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            MyFile myFile = (MyFile) objectInputStream.readObject();
            myFile.setId(fileId);
            FileReceiverController.files.add(myFile);
            System.out.println("File received: " + myFile.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start() {

    }
}
*/