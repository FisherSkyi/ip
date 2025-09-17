package seb;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Seb seb;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image sebImage = new Image(this.getClass().getResourceAsStream("/images/DaSeb.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /** Injects the Seb instance */
    public void setSeb(Seb d) {
        seb = d;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Seb's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() throws UnknownInputException, WrongDescriptionException {
        String userText = userInput.getText();
        if (!userText.trim().equalsIgnoreCase("bye")) {
            String sebText = seb.getResponse(userInput.getText());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getSebDialog(sebText, sebImage)
            );
            userInput.clear();
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getSebDialog("Bye! See you soon.", sebImage)
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
}

