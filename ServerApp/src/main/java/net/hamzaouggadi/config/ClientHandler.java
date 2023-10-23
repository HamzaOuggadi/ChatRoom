package net.hamzaouggadi.config;

import net.hamzaouggadi.services.SendMessage;
import net.hamzaouggadi.services.impl.SendMessageImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ClientHandler implements Runnable {

    private final SendMessage sendMessage;
    private final BufferedReader reader;
    private final List<PrintWriter> clientPrinters;
    private final SocketChannel socket;


    public ClientHandler(SocketChannel socket, List<PrintWriter> clientPrinters) {
        this.socket = socket;
        this.sendMessage = new SendMessageImpl();
        this.clientPrinters = clientPrinters;
        this.reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                System.out.println("read : " + message);
                sendMessage.sendToEveryone(message, clientPrinters);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
