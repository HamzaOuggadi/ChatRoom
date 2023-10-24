package net.hamzaouggadi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.hamzaouggadi.config.NetworkSetup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientApp extends Application {

    private NetworkSetup networkSetup;
    private BufferedReader reader;
    private PrintWriter writer;
    private TextField messageTextField;
    private TextArea chatTextArea;


    public static void main(String[] args) {



/*
        clientApp.setupWriter();
*/





        launch(args);

    }

/*    public void setupWriter() {
        writer = new PrintWriter(Channels.newWriter(networkSetup.getSocketChannel(), StandardCharsets.UTF_8));
    }*/

    public void setWriter2(SocketChannel clientSocket) {
        writer = new PrintWriter(Channels.newWriter(clientSocket, StandardCharsets.UTF_8));
    }


    public void setupNetwork() {

        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 8080);
            SocketChannel clientSocket = SocketChannel.open(serverAddress);
            reader = new BufferedReader(Channels.newReader(clientSocket, StandardCharsets.UTF_8));

            setWriter2(clientSocket);

            System.out.println("Network Established");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage() {
        System.out.println("SendMessage Method : " + writer);
        writer.println(messageTextField.getText());
        writer.flush();
        messageTextField.setText("");
    }

    @Override
    public void start(Stage stage) {


        try {
            InetSocketAddress serverAddress = new InetSocketAddress("oddest.ddns.net", 8080);
            SocketChannel clientSocket = SocketChannel.open(serverAddress);
            reader = new BufferedReader(Channels.newReader(clientSocket, StandardCharsets.UTF_8));
            writer = new PrintWriter(Channels.newWriter(clientSocket, StandardCharsets.UTF_8));
            setWriter2(clientSocket);

            System.out.println("Network Established");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        Image icon = new Image("icon.png");

        VBox parentVBox = new VBox();

        Menu fileMenu = new Menu("Options");
        Menu aboutMenu = new Menu("About");
        MenuBar menuBar = new MenuBar(fileMenu, aboutMenu);
        TextArea chatTextArea = new TextArea("");
        chatTextArea.setPrefHeight(365d);
        messageTextField = new TextField("Type your message here...");
        messageTextField.setPrefWidth(500d);
        Button sendButton = new Button("Send Message");

        VBox topVBox = new VBox(menuBar);

        VBox textAreaVBox = new VBox(chatTextArea);

        HBox messagingHBox = new HBox(messageTextField, sendButton);
        messagingHBox.setSpacing(10d);


        VBox.setMargin(textAreaVBox, new Insets(20d, 20d, 0d, 20d));
        VBox.setMargin(messagingHBox, new Insets(20d, 20d, 0d, 20d));

        parentVBox.getChildren().addAll(topVBox, textAreaVBox, messagingHBox);

        Scene scene = new Scene(parentVBox,640d, 480d, Color.LIGHTSKYBLUE);

        stage.setScene(scene);
        stage.setTitle("ChatRoom");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();


        messageTextField.setOnMouseClicked(mouseEvent -> messageTextField.setText(""));
        sendButton.setOnAction(actionEvent -> sendMessage());
    }
}