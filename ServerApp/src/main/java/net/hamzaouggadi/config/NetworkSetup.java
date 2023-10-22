package net.hamzaouggadi.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NetworkSetup {
    private final ServerSocketChannel serverSocketChannel;
    private final InetSocketAddress serverListenPort;
    private final SocketChannel clientSocket;

    public NetworkSetup(InetSocketAddress serverListenPort) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverListenPort = serverListenPort;
        serverSocketChannel.bind(serverListenPort);
        this.clientSocket = serverSocketChannel.accept();
    }


}
