package tritpo.menu;

import javafx.scene.layout.Pane;


public class Menu extends Pane {

    private static Options optionsMenu = new Options();
    private static MainMenu mainMenu = new MainMenu();
    private static About aboutMenu = new About();

    public Menu() {
        setMainMenu();
    }

    private void setMainMenu()
    {
        this.getChildren().addAll(mainMenu);
    }

    public static About getAboutMenu()
    {
        return aboutMenu;
    }

    public static MainMenu getMainMenu()
    {
        return mainMenu;
    }

    public static Options getOptionMenu()
    {
        return optionsMenu;
    }

}
