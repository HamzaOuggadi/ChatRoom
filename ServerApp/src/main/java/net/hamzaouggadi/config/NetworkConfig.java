package net.hamzaouggadi.config;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;


public class NetworkConfig {

    // This Class has a Singleton Pattern
    private static NetworkConfig networkConfig = null;
    private final static int PORT = 8080;
    private final ServerSocketChannel serverSocketChannel;
    private final InetSocketAddress serverPort;


    public NetworkConfig() throws IOException {
        this.serverPort = new InetSocketAddress(PORT);
        this.serverSocketChannel = ServerSocketChannel.open();
    }

    public static synchronized NetworkConfig getInstance() throws IOException {
        if (networkConfig == null) networkConfig = new NetworkConfig();
        return networkConfig;
    }


    public static NetworkConfig getNetworkConfig() {
        return networkConfig;
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public InetSocketAddress getServerPort() {
        return serverPort;
    }
}
