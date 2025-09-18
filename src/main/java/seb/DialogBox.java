package seb;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private StackPane profilePictureContainer;
    /**
     * Constructor for DialogBox.
     * @param text The text to be displayed in the dialog box.
     * @param img The image of user or Seb to be displayed in the dialog box.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        // Make the profile picture circular
        Circle circleClip = new Circle(
            displayPicture.getFitWidth() / 2,
            displayPicture.getFitHeight() / 2,
            Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2
        );
        displayPicture.setClip(circleClip);
        // Add shadow effect to the profile picture container
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.rgb(0, 0, 0, 0.2));
        profilePictureContainer.setEffect(shadow);
    }
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    public static DialogBox getSebDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
