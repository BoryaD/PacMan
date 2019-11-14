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
    public static final String SCORE = "SCORE: ";
    public static final String LIVES = "LIVES:";
    public static final String PK_TO_START = "PRESS ANY KEY\n   TO START!";
    public static final String PAUSE = "PAUSED";
    public static final String YOU_LOSE = "YOU LOSE";
    public static final String YOU_WIN = "YOU WIN!";
    public static final String CONTINUE = "CONTINUE";
    public static final String RESTART = "RESTART";

    public static final int WINDOW_WIDTH = 528;
    public static final int WINDOW_HEIGHT = 624;
    public static final int WIDTH_OF_TOGGLE_BUTTON = 60;
    public static final int HEIGHT_OF_TOGGLE_BUTTON = 30;

    public static int GHOST_NUMBER = 3;
    public static final int EASY_GHOSTS = 2;
    public static final int MEDIUM_GHOSTS = 3;
    public static final int HARD_GHOSTS = 4;
    public static boolean autoMod = false;

    public static final int MOVING = 1;
    public static final int STOPPED = 0;
    public static final int MOVE_LEFT = 0;
    public static final int MOVE_UP = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_DOWN = 3;
    public static final int TRAPPED = 10;
    public static final int FLASH_HOLLOW_MAX_TIME = 80;
    public static final int HOLLOW_MAX_TIME = 80;
    public static final double CHANGE_FACTOR = 0.75D;
    public static final double CHANGE_FACTOR_2 = 0.5D;
    public static final int IMAGE_OFFSET = -13;
    public static final int FONT_SIZE = 27;
    public static final int INITIAL_PACMAN_X = 15;
    public static final int INITIAL_PACMAN_Y = 24;
    public static final int LITTLE_DOT_RADIUS = 1;
    public static final int BIG_DOT_RADIUS = 5;



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
    public static final int START_SCORE = 500;

    public static final int X_PIXELS = 30;
    public static final int Y_PIXELS = 32;
    private static final int[][] MAZE_DATA = new int[X_PIXELS][Y_PIXELS];
    private static final Object[][] DOT_POINTERS = new Object[X_PIXELS][Y_PIXELS];
    public static SimpleIntegerProperty livesCount = new SimpleIntegerProperty(2);
    private static int dotTotal = 0;
    public static final int PIXEL_SIZE = 16;
    public static final int OFFSET = 32;
    public static final int RECTANNGLE_ARC_WIDTH = 3;
    public static final int RECTANGLE_STROKE_WIDTH = 2;
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

    public static float calcGrid(float x) {
        return PIXEL_SIZE* x + OFFSET;
    }

    public static int calcGridInt(int x) {
        return PIXEL_SIZE * x + OFFSET;
    }

    private static int makeInRange(int gridSize, char coordinate) {
        if (gridSize < 0) {
            return 0;
        } else if (coordinate == 'X' && gridSize > X_PIXELS-1) {
            return X_PIXELS-1;
        } else {
            return coordinate == 'Y' && gridSize > Y_PIXELS-1 ? Y_PIXELS-1 : gridSize;
        }
    }

    public static void resetMaze() {
        dotTotal = 0;
        livesCount = new SimpleIntegerProperty(2);;
        for(int y = 0; y <= Y_PIXELS-1; ++y) {
            for(int x = 0; x <= X_PIXELS-1; ++x) {
                MAZE_DATA[x][y] = 0;
            }
        }
    }

    public static void setBlockMazeData(int x1, int y1, int x2, int y2) {
        x1 = makeInRange(x1, 'X');
        y1 = makeInRange(y1, 'Y');
        x2 = makeInRange(x2, 'X');
        y2 = makeInRange(y2, 'Y');

        int i;
        for(i = x1; i <= x2; ++i) {
            MAZE_DATA[i][y1] = 1;
            MAZE_DATA[i][y2] = 1;
        }

        for(i = y1; i <= y2; ++i) {
            MAZE_DATA[x1][i] = 1;
            MAZE_DATA[x2][i] = 1;
        }
    }

    public static int getData(int x, int y) {
        return MAZE_DATA[x][y];
    }

    public static void setData(int x, int y, int value) {
        MAZE_DATA[x][y] = value;
        if (value == 3 || value == 2) {
            ++dotTotal;
        }
    }

    public static int getDotTotal() {
        return dotTotal;
    }

    public static Object getDot(int x, int y) {
        return DOT_POINTERS[x][y];
    }

    public static void setDot(int x, int y, Object dot) {
        DOT_POINTERS[x][y] = dot;
    }
}

