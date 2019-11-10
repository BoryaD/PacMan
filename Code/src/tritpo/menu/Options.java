package tritpo.menu;

import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tritpo.Data;
import tritpo.menu.*;

public class Options extends Pane {

    private static HBox optionsTitle;
    private static HBox backBox;
    private static HBox levelButtonsBox;
    private static HBox modBox;

    public Options() {
        MenuContentInMiddle title = new MenuContentInMiddle(Data.OPTIONS);
        optionsTitle = new HBox(title);
        optionsTitle.setPrefWidth((double) Data.WINDOW_WIDTH);
        optionsTitle.setAlignment(Pos.CENTER);
        optionsTitle.setLayoutY(Data.WINDOW_WIDTH/4);

        MenuButton backButton = new MenuButton(Data.BACK);
        backBox = new HBox(backButton);
        backBox.setPrefWidth((double)Data.WINDOW_WIDTH);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.setLayoutY(Data.WINDOW_HEIGHT- 2*Data.ANCHORE);
        backBox.setLayoutX(-Data.ANCHORE);

        Text levelText = new Text(Data.LEVEL);
        levelText.setFont(Data.PACKMAN_FONT_OPTIONS);
        levelText.setFill(Color.WHITE);
        LevelButton easyButton = new LevelButton(Data.EASY, Data.EASY_GHOSTS, Color.GREEN);
        LevelButton mediumButton = new LevelButton(Data.MEDIUM, Data.MEDIUM_GHOSTS, Color.YELLOW);
        LevelButton hardButton = new LevelButton(Data.HARD, Data.HARD_GHOSTS, Color.RED);

        Text modText = new Text(Data.AUTO_MODE);
        modText.setFont(Data.PACKMAN_FONT_MENU);
        modText.setFill(Color.WHITE);
        MyToggleButton autoMode = new MyToggleButton();
        modBox = new HBox(Data.ANCHORE, modText, autoMode);
        modBox.setTranslateY(Data.WINDOW_HEIGHT/1.5);
        modBox.setTranslateX(Data.ANCHORE);

        easyButton.setOnMouseClicked((event) -> {
            easyButton.setLevel();
            easyButton.setColor(Color.GREEN);
            mediumButton.setColor(Color.WHITE);
            hardButton.setColor(Color.WHITE);
        });
        mediumButton.setOnMouseClicked((event) -> {
            mediumButton.setLevel();
            easyButton.setColor(Color.WHITE);
            mediumButton.setColor(Color.YELLOW);
            hardButton.setColor(Color.WHITE);
        });
        hardButton.setOnMouseClicked((event) -> {
            hardButton.setLevel();
            easyButton.setColor(Color.WHITE);
            mediumButton.setColor(Color.WHITE);
            hardButton.setColor(Color.RED);
        });
        levelButtonsBox = new HBox(Data.SPACES_BETWEEN_BUTTONS, levelText, easyButton, mediumButton, hardButton);
        levelButtonsBox.setTranslateY(Data.WINDOW_HEIGHT/2);
        levelButtonsBox.setTranslateX(Data.ANCHORE);

        backButton.setOnMouseClicked((event) -> {
            this.setMenu();
        });
        this.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                this.setMenu();
            }
        });
        this.getChildren().addAll(backBox,optionsTitle, levelButtonsBox, modBox);
    }

    public void setMenu() {
        MainMenu menuScreen = Menu.getMainMenu();
        Data.content.getChildren().clear();
        Data.content.getChildren().add(menuScreen);
        menuScreen.requestFocus();
    }
}