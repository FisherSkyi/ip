package seb;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, Seb!");
        Scene scene = new Scene(label);
        stage.setScene(scene);
        stage.show();
    }
}
