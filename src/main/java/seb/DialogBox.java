package seb;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    
    public DialogBox(String text, Image img) {
        this.text = new Label(text);
        this.displayPicture = new ImageView(img);
        this.getChildren().addAll(this.text, this.displayPicture);
    }
}
