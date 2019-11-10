package tritpo.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tritpo.*;

public class MainMenu extends Pane {

    private static HBox menuTitle;
    private static HBox menuButtons;

    public MainMenu()
    {
        MenuContentInMiddle title = new MenuContentInMiddle(Data.PAC_MAN);
        menuTitle = new HBox(title);
        menuTitle.setPrefWidth((double)Data.WINDOW_WIDTH);
        menuTitle.setAlignment(Pos.CENTER);
        menuTitle.setLayoutY(Data.WINDOW_HEIGHT/4);
        VBox buttonsBox = new VBox(Data.SPACES_BETWEEN_BUTTONS);
        MenuButton playButton = new MenuButton(Data.GAME);
        MenuButton optionsButton = new MenuButton(Data.OPTIONS);
        MenuButton aboutButton = new MenuButton(Data.ABOUT);
        MenuButton exitButton = new MenuButton(Data.EXIT);
        buttonsBox.getChildren().addAll(playButton, optionsButton, aboutButton, exitButton);

        playButton.setOnMouseClicked((event) -> {

        });
        optionsButton.setOnMouseClicked((event) -> {
            Options optionScreen = Menu.getOptionMenu();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(optionScreen);
            optionScreen.requestFocus();
        });
        aboutButton.setOnMouseClicked((event) -> {
            About aboutScreen = Menu.getAboutMenu();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(aboutScreen);
            aboutScreen.requestFocus();
        });
        exitButton.setOnMouseClicked((event) -> {
            System.exit(0);
        });
        menuButtons = new HBox(buttonsBox);
        menuButtons.setPrefWidth((double)Data.WINDOW_WIDTH);
        menuButtons.setAlignment(Pos.CENTER);
        menuButtons.setTranslateY(Data.WINDOW_HEIGHT/2);
        this.getChildren().addAll(menuTitle, menuButtons);
    }

}
