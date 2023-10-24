package net.hamzaouggadi;




import net.hamzaouggadi.config.AppProperties;
import net.hamzaouggadi.config.ClientHandler;
import net.hamzaouggadi.config.NetworkConfig;

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
    static NetworkConfig networkConfig;

    public static void main(String[] args) throws IOException {

        System.out.println("Server Starting...");

        ServerApp serverApp = new ServerApp();

        networkConfig = NetworkConfig.getInstance();

        serverApp.serverGo();

    }


    public void serverGo() {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            ServerSocketChannel serverSocketChannel = networkConfig.getServerSocketChannel();
            serverSocketChannel.bind(networkConfig.getServerPort());

            while (serverSocketChannel.isOpen()) {
                SocketChannel clientSocket = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, StandardCharsets.UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket, clientWriters));
                System.out.println("New Client Connected");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

}