package net.hamzaouggadi.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private final InputStream inputFile;
    private final Properties properties;
    private int serverPort;

    public AppProperties() throws IOException {
        this.properties = new Properties();
        this.inputFile = new FileInputStream("ServerApp/src/main/resources/config.properties");
        properties.load(inputFile);
    }

    public InputStream getInputFile() {
        return inputFile;
    }

    public Properties getProperties() {
        return properties;
    }

    public int getServerPort() {
        return Integer.parseInt(properties.getProperty("server.port"));
    }

    public void setServerPort(int serverPort) {
        properties.setProperty("server.port", String.valueOf(serverPort));
    }
}
