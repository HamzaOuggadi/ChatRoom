package net.hamzaouggadi.services;

import java.io.PrintWriter;
import java.util.List;

public interface SendMessage {

    void sendToEveryone(String message, List<PrintWriter> clientWriters);
}
