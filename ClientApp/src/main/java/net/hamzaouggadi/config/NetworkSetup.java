package net.hamzaouggadi.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class NetworkSetup {

    private final InetSocketAddress serverAddress;
    private final SocketChannel socketChannel;
    private final int serverPort;


    public NetworkSetup(int serverPort) throws IOException {
        this.serverAddress = new InetSocketAddress(serverPort);
        this.socketChannel = SocketChannel.open(serverAddress);
        this.serverPort = serverPort;
    }

    public NetworkSetup(InetSocketAddress serverAddress) throws IOException {
        this.serverAddress = serverAddress;
        this.socketChannel = SocketChannel.open(serverAddress);
        this.serverPort = serverAddress.getPort();
    }


    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public int getServerPort() {
        return serverPort;
    }
}
