package com.loca.addressbook.remoteregistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class CatalogueClient {

    private String host;
    private int port;
    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;

    public CatalogueClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendRequest(String request){
        writer.println(request);
        writer.flush();
    }


    //TODO change name?
    public String waitForResponse() throws IOException {
        String stringLine= "";
     // for (String line = reader.readLine(); !line.equals("EOF"); line = reader.readLine()) {
          for (String line = reader.readLine(); line!=null; line = reader.readLine()) { //can fix EOF line split problem in array adding to list
            stringLine += line + "\n";
        }
        return  stringLine;
    }


    //TODO strange throw
    public void disconnect() throws IOException, NullPointerException {
        reader.close();
        writer.close();
        socket.close();
    }
}
