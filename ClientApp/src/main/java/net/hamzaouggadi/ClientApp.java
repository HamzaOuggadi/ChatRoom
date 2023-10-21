package net.hamzaouggadi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientApp extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Image icon = new Image("icon.png");

        VBox parentVBox = new VBox();

        Menu fileMenu = new Menu("File");
        Menu aboutMenu = new Menu("About");
        MenuBar menuBar = new MenuBar(fileMenu, aboutMenu);
        TextArea chatTextArea = new TextArea("Chat Text");

        VBox topVBox = new VBox(menuBar);

        VBox textAreaVBox = new VBox(chatTextArea);

        VBox.setMargin(textAreaVBox, new Insets(20));

        parentVBox.getChildren().addAll(topVBox, textAreaVBox);

        Scene scene = new Scene(parentVBox,640, 480, Color.LIGHTSKYBLUE);

        stage.setScene(scene);
        stage.setTitle("ChatRoom");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }
}