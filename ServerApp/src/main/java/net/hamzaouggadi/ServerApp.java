package net.hamzaouggadi;




import net.hamzaouggadi.config.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerApp {

    private final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.out.println("Server Starting...");

        ServerApp serverApp = new ServerApp();

        serverApp.serverGo();

    }


    public void serverGo() {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));

            while (serverSocketChannel.isOpen()) {
                SocketChannel clientSocket = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, StandardCharsets.UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new net.hamzaouggadi.config.ClientHandler(clientSocket, clientWriters));
                System.out.println("New Client Connected");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        SocketChannel socket;

        public ClientHandler(SocketChannel socket) {
            this.socket = socket;
            this.reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read : " + message);
                    sendToEveryone(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendToEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

}