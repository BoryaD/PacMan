package tritpo.menu;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tritpo.*;

public class About extends Pane {
    private static HBox aboutTitle;
    private static TextArea aboutField;
    private static HBox backBox;

    public About() {
        MenuContentInMiddle title = new MenuContentInMiddle(Data.ABOUT);
        aboutTitle = new HBox(title);
        aboutTitle.setPrefWidth((double)Data.WINDOW_WIDTH);
        aboutTitle.setAlignment(Pos.CENTER);
        aboutTitle.setLayoutY(Data.WINDOW_HEIGHT/4);
        aboutField = new TextArea(Data.ABOUT_STR);
        aboutField.setEditable(false);
        aboutField.setLayoutY(Data.WINDOW_WIDTH/2);
        aboutField.setLayoutX(Data.ANCHORE);
        aboutField.setPrefSize((Data.WINDOW_WIDTH - 2*Data.ANCHORE), Data.WINDOW_WIDTH/2);
        MenuButton backButton = new MenuButton(Data.BACK);
        backBox = new HBox(backButton);
        backBox.setPrefWidth((double)Data.WINDOW_WIDTH);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.setLayoutY(Data.WINDOW_HEIGHT- 2*Data.ANCHORE);
        backBox.setLayoutX(-Data.ANCHORE);
        backButton.setOnMouseClicked((event) -> {
            this.setMenu();
        });
        this.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                this.setMenu();
            }
        });
        this.getChildren().addAll(aboutTitle, aboutField, backBox);
    }

    public void setMenu() {
        MainMenu menuScreen = Menu.getMainMenu();
        Data.content.getChildren().clear();
        Data.content.getChildren().add(menuScreen);
        menuScreen.requestFocus();
    }
}
