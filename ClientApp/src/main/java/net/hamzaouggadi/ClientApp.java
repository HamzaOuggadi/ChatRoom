package net.hamzaouggadi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientApp extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Image icon = new Image("icon.png");
        Button button = new Button("Click !");
        button.setOnAction(actionEvent -> {
            button.setText("You Just Clicked Me !");
        });
        HBox hbox = new HBox(button);


        Scene scene = new Scene(hbox, 640, 480, Color.LIGHTSKYBLUE);

        stage.setScene(scene);
        stage.setTitle("ChatRoom");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();
    }
}