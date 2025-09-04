package seb;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.util.Duration;


public class Main extends Application{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private Seb seb = new Seb("data/seb.txt");
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image sebImage = new Image(this.getClass().getResourceAsStream("/images/DaSeb.png"));
    
    @FXML
    public void handleUserInput() throws UnknownInputException, WrongDescriptionException {
        String userText = userInput.getText();
        if (!userText.trim().equalsIgnoreCase("bye")) {
            String sebText = seb.getResponse(userInput.getText());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getDukeDialog(sebText, sebImage)
            );
            userInput.clear();
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("Bye! See you soon.", sebImage)
            );
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.millis(400));
            delay.setOnFinished(e -> {
                 Stage stage = (Stage) userInput.getScene().getWindow();
                 stage.close();
                 javafx.application.Platform.exit(); // shut down JavaFX runtime
            });

            delay.play();
        }
    }
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(seb);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
