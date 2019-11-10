package tritpo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import static java.lang.System.exit;

public final class Data {
    private Data(){}
    public static Group content = new Group();

    public static final String APPLICATION_TITLE = "PacMan";
    public static final String PAC_MAN = "PAC-MAN";
    public static final String OPTIONS = "OPTIONS";
    public static final String ABOUT = "ABOUT";
    public static final String GAME = "GAME";
    public static final String EXIT = "EXIT";
    public static final String BACK = "BACK";
    public static final String LEVEL = "Level :";
    public static final String EASY = "Easy";
    public static final String MEDIUM = "Medium";
    public static final String HARD = "Hard";
    public static final String AUTO_MODE = "AUTO-MODE :";

    public static final int WINDOW_WIDTH = 528;
    public static final int WINDOW_HEIGHT = 624;
    public static final int WIDTH_OF_TOGGLE_BUTTON = 60;
    public static final int HEIGHT_OF_TOGGLE_BUTTON = 30;

    public static int GHOST_NUMBER = 3;
    public static final int EASY_GHOSTS = 2;
    public static final int MEDIUM_GHOSTS = 3;
    public static final int HARD_GHOSTS = 4;
    public static boolean autoMod = false;

    public static final double TIME_OF_FADING = 3.0;
    public static final int DURATION_MILLS = 45;
    public static final double START_FADE_VALUE = 0.0;
    public static final double FINAL_FADE_VALUE = 1.0;
    public static final double REFLECTION_VALUE = 1.0;
    public static final double SPACES_BETWEEN_BUTTONS = 20.0;
    public static final double INCRECED_SIZE_OF_BUTTON = 1.1;
    public static final double NORMAL_SIZE_OF_BUTTON = 1.0;
    public static final double TIME_OF_INCREASING = 1.25;
    public static final double RADIUS_OF_SHADOW = 20.0;
    public static final double ANCHORE = 40.0;


    public static final int X_PIXELS = 30;
    public static final int Y_PIXELS = 32;
    public static Image PACMAN_IMAGE = null;
    public static Font PACKMAN_FONT_TITLE = null;
    public static Font PACKMAN_FONT_OPTIONS = null;
    public static Font PACKMAN_FONT_MENU = null;
    public static Font PACKMAN_FONT_MESSAGE = null;


    static {

        try {
            PACMAN_IMAGE = new Image("file:resources/images/left1.png");
            PACKMAN_FONT_TITLE = Font.loadFont("file:resources/fonts/PacFont.ttf", 75.0D);
            PACKMAN_FONT_OPTIONS = Font.loadFont("file:resources/fonts/PacFont.ttf", 20.0D);
            PACKMAN_FONT_MENU = Font.loadFont("file:resources/fonts/PacFont.ttf", 30.0D);
            PACKMAN_FONT_MESSAGE = Font.loadFont("file:resources/fonts/PacFont.ttf", 50.0D);

        } catch (NullPointerException ex) {
            exit(1);
        }
    }

    public static final String ABOUT_STR = "The player controls a circular character which has\na pie wedge shaped mouth to eat pellets through\na maze, eating Pac-Dots. When all dots are eaten,\nPac-Man is taken to the next stage. Four ghosts,\nBlinky, Pinky, Inky and Clyde roam the maze,\ntrying to catch Pac-Man. If a ghost chomps (touches)\n Pac-Man, a life is lost. When all lives have been\n lost,the game ends. Pac-Man is awarded a single\n bonus life at 10,000 points by default DIP switches\n inside the machine can change the required points\n or disable the bonus life altogether.\n";

}

