package net.hamzaouggadi.services.impl;

import net.hamzaouggadi.services.SendMessage;

import java.io.PrintWriter;
import java.util.List;

public class SendMessageImpl implements SendMessage {


    @Override
    public void sendToEveryone(String message, List<PrintWriter> clientWriters) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }
}
