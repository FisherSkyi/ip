package seb;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Seb seb = new Seb("data/seb.txt");
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image sebImage = new Image(this.getClass().getResourceAsStream("/images/DaSeb.png"));
    
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
            });
            delay.play();
        }

    }
    
    @Override
    public void start(Stage stage) {


        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer); // dialogContainer is content of scrollPane
        
        userInput = new TextField();
        sendButton = new Button("send");
        
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (UnknownInputException e) {
                throw new RuntimeException(e);
            } catch (WrongDescriptionException e) {
                throw new RuntimeException(e);
            }
        });
        
        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (UnknownInputException e) {
                throw new RuntimeException(e);
            } catch (WrongDescriptionException e) {
                throw new RuntimeException(e);
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton); // mainLayout is composed of these 3
        
        scene = new Scene(mainLayout);
        
        stage.setScene(scene);
        stage.setTitle("Seb");
        stage.setResizable(false);
        stage.setMinHeight(600.00);
        stage.setMinWidth(400.00);
        mainLayout.setPrefSize(400.00, 600.00);
        
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(325.0);
        
        sendButton.setPrefWidth(55.0);
        
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        
        stage.show();
    }
}
