package tritpo.gameLogic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tritpo.Data;
import tritpo.menu.Menu;

public class Maze extends Parent {
    public PacMan pacMan;
    public final Ghost[] ghosts;
    public final Ghost[] allGhosts;
    private int ghostEatenCount;
    public BooleanProperty waitForStart;
    private final MessageBox messageStartBox;
    private final MessageBox messagePauseBox;
    private final MessageBox messageLoseBox;
    public final MessageBox messageWinBox;
    public BooleanProperty gamePaused;
    private final Group group;

    public Maze() {
        this.setFocused(true);
        Data.resetMaze();
        this.pacMan = new PacMan(this, Data.INITIAL_PACMAN_X, Data.INITIAL_PACMAN_Y);
        this.group = new Group();
        this.gamePaused = new SimpleBooleanProperty(false);
        this.waitForStart = new SimpleBooleanProperty(true);

        Ghost ghostBlinky = new Ghost(new Image("file:resources/images/ghostred1.png"), new Image("file:resources/images/ghostred2.png"), this, this.pacMan, 15, 14, 0, -1, 1);
        Ghost ghostPinky = new Ghost(new Image("file:resources/images/ghostpink1.png"), new Image("file:resources/images/ghostpink2.png"), this, this.pacMan, 14, 15, 1, 0, 5);
        Ghost ghostInky = new Ghost(new Image("file:resources/images/ghostcyan1.png"), new Image("file:resources/images/ghostcyan2.png"), this, this.pacMan, 12, 15, 1, 0, 20);
        Ghost ghostClyde = new Ghost(new Image("file:resources/images/ghostorange1.png"), new Image("file:resources/images/ghostorange2.png"), this, this.pacMan, 16, 15, 1, 0, 30);
        this.allGhosts = new Ghost[]{ghostBlinky, ghostPinky, ghostInky, ghostClyde};
        this.ghosts = new Ghost[Data.GHOST_NUMBER];

        for(int i = 0; i < Data.GHOST_NUMBER; ++i) {
            this.ghosts[i] = this.allGhosts[i];
        }

        Text textScore = new Text(Data.SCORE + pacMan.score);
        textScore.textProperty().bind(pacMan.score.asString(Data.SCORE +" %1d "));
        textScore.setFont(new Font(Data.FONT_SIZE));
        textScore.setFill(Color.YELLOW);
        textScore.setCache(true);
        HBox scoreView = new HBox(10.0D, textScore);
        scoreView.setTranslateX((double)Data.calcGrid(0.0F));
        scoreView.setTranslateY((double)Data.calcGrid(32.0F));

        ImageView livesImage1 = new ImageView(Data.PACMAN_IMAGE);
        livesImage1.visibleProperty().bind(Data.livesCount.greaterThan(-1));
        livesImage1.setTranslateY(5.0D);
        livesImage1.setCache(true);
        ImageView livesImage2 = new ImageView(Data.PACMAN_IMAGE);
        livesImage2.visibleProperty().bind(Data.livesCount.greaterThan(0));
        livesImage2.setTranslateY(5.0D);
        livesImage2.setCache(true);
        ImageView livesImage3 = new ImageView(Data.PACMAN_IMAGE);
        livesImage3.visibleProperty().bind(Data.livesCount.greaterThan(1));
        livesImage3.setTranslateY(5.0D);
        livesImage3.setCache(true);
        Text textLives = new Text(Data.LIVES);
        textLives.setFont(new Font(Data.FONT_SIZE));
        textLives.setFill(Color.YELLOW);
        HBox livesView = new HBox(10.0D, new Node[]{textLives, livesImage1, livesImage2, livesImage3});
        livesView.setTranslateX((double)Data.calcGrid(17.7F));
        livesView.setTranslateY((double)Data.calcGrid(32.0F));
        this.messageStartBox = new MessageBox(this, Data.PK_TO_START);
        this.messagePauseBox = new MessageBox(this, Data.PAUSE);
        this.messageLoseBox = new MessageBox(this, Data.YOU_LOSE);
        this.messageWinBox = new MessageBox(this, Data.YOU_WIN);
        this.messagePauseBox.setVisible(false);
        this.messageLoseBox.setVisible(false);
        this.messageWinBox.setVisible(false);
        this.setMaze(this.group);
        this.setDots();
        this.setOnKeyPressed((event) -> {
            this.onKeyPressed(event);
        });
        this.group.getChildren().add(this.pacMan);
        this.group.getChildren().add(scoreView);
        this.group.getChildren().addAll(this.ghosts);
        this.group.getChildren().addAll(this.messageStartBox, this.messagePauseBox, this.messageLoseBox, this.messageWinBox);
        this.group.getChildren().addAll(livesView);
        this.getChildren().add(this.group);
    }

    public void setMaze(Group group) {
        group.getChildren().add(new WallRectangle(-0.5F, -0.5F, 29.5F, 31.5F));
        group.getChildren().add(new WallRectangle(0.0F, 0.0F, 29.0F, 31.0F));
        group.getChildren().add(new WallRectangle(14.0F, -0.5F, 15.0F, 4.0F));
        group.getChildren().add(new WallBlackRectangle(13.8F, -0.5F, 15.3F, 0.0F));
        group.getChildren().add(new WallRectangle(2.0F, 2.0F, 5.0F, 4.0F));
        group.getChildren().add(new WallRectangle(7.0F, 2.0F, 12.0F, 4.0F));
        group.getChildren().add(new WallRectangle(17.0F, 2.0F, 22.0F, 4.0F));
        group.getChildren().add(new WallRectangle(24.0F, 2.0F, 27.0F, 4.0F));
        group.getChildren().add(new WallRectangle(2.0F, 6.0F, 5.0F, 7.0F));
        group.getChildren().add(new WallRectangle(14.0F, 6.0F, 15.0F, 10.0F));
        group.getChildren().add(new WallRectangle(10.0F, 6.0F, 19.0F, 7.0F));
        group.getChildren().add(new WallBlackRectangle(14.0F, 6.5F, 15.0F, 7.5F));
        group.getChildren().add(new WallRectangle(7.5F, 9.0F, 12.0F, 10.0F));
        group.getChildren().add(new WallRectangle(7.0F, 6.0F, 8.0F, 13.0F));
        group.getChildren().add(new WallBlackRectangle(7.5F, 9.0F, 8.5F, 10.0F));
        group.getChildren().add(new WallRectangle(17.0F, 9.0F, 21.5F, 10.0F));
        group.getChildren().add(new WallRectangle(21.0F, 6.0F, 22.0F, 13.0F));
        group.getChildren().add(new WallBlackRectangle(20.5F, 9.0F, 21.5F, 10.0F));
        group.getChildren().add(new WallRectangle(24.0F, 6.0F, 27.0F, 7.0F));
        group.getChildren().add(new WallRectangle(10.0F, 12.0F, 19.0F, 17.0F));
        group.getChildren().add(new WallRectangle(10.5F, 12.5F, 18.5F, 16.5F));
        Rectangle cageRect = new Rectangle((double)Data.calcGrid(13.0F), (double)Data.calcGrid(12.0F), 48.0D, 8.0D);
        cageRect.setStroke(Color.GREY);
        cageRect.setFill(Color.GREY);
        cageRect.setCache(true);
        group.getChildren().add(cageRect);
        group.getChildren().add(new WallRectangle(7.0F, 15.0F, 8.0F, 20.0F));
        group.getChildren().add(new WallRectangle(21.0F, 15.0F, 22.0F, 20.0F));
        group.getChildren().add(new WallRectangle(14.0F, 19.0F, 15.0F, 23.0F));
        group.getChildren().add(new WallRectangle(10.0F, 19.0F, 19.0F, 20.0F));
        group.getChildren().add(new WallBlackRectangle(14.0F, 19.5F, 15.0F, 20.5F));
        group.getChildren().add(new WallRectangle(4.0F, 22.0F, 5.0F, 26.0F));
        group.getChildren().add(new WallRectangle(2.0F, 22.0F, 5.0F, 23.0F));
        group.getChildren().add(new WallBlackRectangle(4.0F, 22.05F, 5.0F, 23.2F));
        group.getChildren().add(new WallRectangle(7.0F, 22.0F, 12.0F, 23.0F));
        group.getChildren().add(new WallRectangle(24.0F, 22.0F, 25.0F, 26.0F));
        group.getChildren().add(new WallRectangle(24.0F, 22.0F, 27.0F, 23.0F));
        group.getChildren().add(new WallBlackRectangle(24.0F, 22.05F, 25.0F, 23.2F));
        group.getChildren().add(new WallRectangle(17.0F, 22.0F, 22.0F, 23.0F));
        group.getChildren().add(new WallRectangle(-0.5F, 25.0F, 2.0F, 26.0F));
        group.getChildren().add(new WallRectangle(27.0F, 25.0F, 29.5F, 26.0F));
        group.getChildren().add(new WallRectangle(7.0F, 25.0F, 8.0F, 29.0F));
        group.getChildren().add(new WallRectangle(2.0F, 28.0F, 12.0F, 29.0F));
        group.getChildren().add(new WallBlackRectangle(7.0F, 27.5F, 8.0F, 28.5F));
        group.getChildren().add(new WallRectangle(14.0F, 25.0F, 15.0F, 29.0F));
        group.getChildren().add(new WallRectangle(10.0F, 25.0F, 19.0F, 26.0F));
        group.getChildren().add(new WallBlackRectangle(14.0F, 25.5F, 15.0F, 26.5F));
        group.getChildren().add(new WallRectangle(21.0F, 25.0F, 22.0F, 29.0F));
        group.getChildren().add(new WallRectangle(17.0F, 28.0F, 27.0F, 29.0F));
        group.getChildren().add(new WallBlackRectangle(21.0F, 27.5F, 22.0F, 28.5F));
        group.getChildren().add(new WallRectangle(-1.0F, 9.0F, 5.0F, 13.0F));
        group.getChildren().add(new WallRectangle(-1.0F, 9.5F, 4.5F, 12.5F));
        group.getChildren().add(new WallRectangle(-1.0F, 15.0F, 5.0F, 20.0F));
        group.getChildren().add(new WallRectangle(-1.0F, 15.5F, 4.5F, 19.5F));
        group.getChildren().add(new WallRectangle(24.0F, 9.0F, 30.0F, 13.0F));
        group.getChildren().add(new WallRectangle(24.5F, 9.5F, 30.0F, 12.5F));
        group.getChildren().add(new WallRectangle(24.0F, 15.0F, 30.0F, 20.0F));
        group.getChildren().add(new WallRectangle(24.5F, 15.5F, 30.0F, 19.5F));
        group.getChildren().add(new WallBlackRectangle(-2.0F, 8.0F, -0.5F, 31.0F));
        group.getChildren().add(new WallBlackRectangle(-0.5F, 8.0F, 0.0F, 9.5F));
        group.getChildren().add(new WallBlackRectangle(-0.5F, 19.5F, 0.0F, 31.0F));
        group.getChildren().add(new WallBlackRectangle(29.5F, 8.0F, 31.0F, 31.0F));
        group.getChildren().add(new WallBlackRectangle(29.0F, 8.0F, 29.5F, 9.5F));
        group.getChildren().add(new WallBlackRectangle(29.0F, 19.5F, 29.5F, 31.0F));
        group.getChildren().add(new WallBlackRectangle(-1.0F, 13.0F, 1.0F, 15.0F));
        group.getChildren().add(new WallBlackRectangle(28.0F, 13.0F, 30.0F, 15.0F));
    }

    public void setDots() {
        this.putDotHorizontally(2, 12, 1);
        this.putDotHorizontally(17, 27, 1);
        this.putDotHorizontally(2, 27, 5);
        this.putDotHorizontally(2, 5, 8);
        this.putDotHorizontally(24, 27, 8);
        this.putDotHorizontally(10, 13, 8);
        this.putDotHorizontally(16, 19, 8);
        this.putDotHorizontally(2, 12, 21);
        this.putDotHorizontally(17, 27, 21);
        this.putDotHorizontally(2, 2, 24);
        this.putDotHorizontally(27, 27, 24);
        this.putDotHorizontally(7, 12, 24);
        this.putDotHorizontally(17, 22, 24);
        this.putDotHorizontally(2, 5, 27);
        this.putDotHorizontally(24, 27, 27);
        this.putDotHorizontally(10, 12, 27);
        this.putDotHorizontally(17, 19, 27);
        this.putDotHorizontally(2, 27, 30);
        this.putDotVertically(1, 1, 8);
        this.putDotVertically(28, 1, 8);
        this.putDotVertically(1, 21, 24);
        this.putDotVertically(28, 21, 24);
        this.putDotVertically(1, 27, 30);
        this.putDotVertically(28, 27, 30);
        this.putDotVertically(3, 24, 27);
        this.putDotVertically(26, 24, 27);
        this.putDotVertically(6, 1, 27);
        this.putDotVertically(23, 1, 27);
        this.putDotVertically(9, 5, 8);
        this.putDotVertically(20, 5, 8);
        this.putDotVertically(9, 24, 27);
        this.putDotVertically(20, 24, 27);
        this.putDotVertically(13, 1, 4);
        this.putDotVertically(16, 1, 4);
        this.putDotVertically(13, 21, 24);
        this.putDotVertically(16, 21, 24);
        this.putDotVertically(13, 27, 30);
        this.putDotVertically(16, 27, 30);
    }

    public final Dot createDot(int x1, int y1, int type) {
        Dot d = new Dot(Data.calcGridInt(x1), Data.calcGridInt(y1), type);
        Data.setData(x1, y1, type);
        Data.setDot(x1, y1, d);
        return d;
    }

    public final void putDotHorizontally(int x1, int x2, int y) {
        for(int x = x1; x <= x2; ++x) {
            if (Data.getData(x, y) == 0) {
                byte dotType;
                if (x != 28 && x != 1 || y != 3 && y != 24) {
                    dotType = 2;
                } else {
                    dotType = 3;
                }

                Dot dot = this.createDot(x, y, dotType);
                this.group.getChildren().add(dot);
            }
        }
    }

    public final void putDotVertically(int x, int y1, int y2) {
        for(int y = y1; y <= y2; ++y) {
            if (Data.getData(x, y) == 0) {
                byte dotType;
                if (x != 28 && x != 1 || y != 3 && y != 24) {
                    dotType = 2;
                } else {
                    dotType = 3;
                }

                Dot dot = this.createDot(x, y, dotType);
                this.group.getChildren().add(dot);
            }
        }
    }

    public void onKeyPressed(KeyEvent event) {
        Maze.СontrolThread сontrolThread = new Maze.СontrolThread(event);
        сontrolThread.start();
    }

    public void changeState(KeyEvent event) {
        int y;
        if (this.messageStartBox.isVisible()) {
            this.pacMan.resetStatus();

            for(int x = 1; x <= 29; ++x) {
                for(y = 1; y <= 31; ++y) {
                    Dot dot = (Dot)Data.getDot(x, y);
                    if (dot != null && !dot.isVisible()) {
                        dot.setVisible(true);
                    }
                }
            }
            for(y = 0; y < Data.GHOST_NUMBER; ++y) {
                this.ghosts[y].resetStatus();
            }

            this.messageStartBox.setVisible(false);
            this.waitForStart.set(false);

        } else if (this.waitForStart.get() && event.getCode() == KeyCode.ESCAPE) {
            this.pacMan.start();
            for(y = 0; y < Data.GHOST_NUMBER; ++y) {
                this.ghosts[y].start();
            }

            this.waitForStart.set(false);
            this.messagePauseBox.setVisible(false);
            this.gamePaused.set(false);
        } else {

            if (!this.waitForStart.get() && event.getCode() == KeyCode.ESCAPE) {
                this.waitForStart.set(true);
                this.messagePauseBox.setVisible(true);
                this.pacMan.pause();
                for(y = 0; y < Data.GHOST_NUMBER; ++y) {
                    this.ghosts[y].pause();
                }

                this.gamePaused.set(true);
            }
            if (!Data.autoMod) {
                if (event.getCode() == KeyCode.DOWN) {
                    this.pacMan.setKeyboardBuffer(3);
                } else if (event.getCode() == KeyCode.UP) {
                    this.pacMan.setKeyboardBuffer(1);
                } else if (event.getCode() == KeyCode.RIGHT) {
                    this.pacMan.setKeyboardBuffer(2);
                } else if (event.getCode() == KeyCode.LEFT) {
                    this.pacMan.setKeyboardBuffer(0);
                }
            }
        }
    }

    public void setMenu() {
        this.pacMan.stop();
        Data.content.getChildren().clear();
        Data.content.getChildren().add(Menu.getMainMenu());
        Menu.getMainMenu().requestFocus();
    }

    public void resume() {
        if (this.pacMan.isPaused()) {
            this.pacMan.start();
        }

        for(int y = 0; y < Data.GHOST_NUMBER; ++y) {
            this.ghosts[y].start();
        }

        this.waitForStart.set(false);
        this.messagePauseBox.setVisible(false);
        this.gamePaused.set(false);
    }

    public void restart() {
        this.pacMan.resetStatus();

        int y;
        for(int x = 1; x <= Data.X_PIXELS-1; ++x) {
            for(y = 1; y <= Data.Y_PIXELS-1; ++y) {
                Dot dot = (Dot)Data.getDot(x, y);
                if (dot != null && !dot.isVisible()) {
                    dot.setVisible(true);
                }
            }
        }

        for(y = 0; y < Data.GHOST_NUMBER; ++y) {
            this.ghosts[y].resetStatus();
        }

        this.messageStartBox.setVisible(false);
        this.waitForStart.set(false);
    }

    public void makeGhostsHollow() {
        this.ghostEatenCount = 0;
        for(int y = 0; y < Data.GHOST_NUMBER; ++y) {
            this.ghosts[y].changeToHollowGhost();
        }

    }

    public boolean hasMet(Ghost ghost) {
        int distanceThreshold = 22;
        int x1 = ghost.imageX.get();
        int x2 = this.pacMan.imageX.get();
        int diffX = Math.abs(x1 - x2);
        if (diffX >= distanceThreshold) {
            return false;
        } else {
            int y1 = ghost.imageY.get();
            int y2 = this.pacMan.imageY.get();
            int diffY = Math.abs(y1 - y2);
            if (diffY >= distanceThreshold) {
                return false;
            } else {
                return diffY * diffY + diffX * diffX <= distanceThreshold * distanceThreshold;
            }
        }
    }

    public void pacManMeetsGhosts() {


        for(int y = 0; y < Data.GHOST_NUMBER; ++y) {
            Ghost g = this.ghosts[y];
            if (this.hasMet(g)) {
                if (g.isHollow) {
                    this.pacManEatsGhost(g);
                } else {
                    if (Data.livesCount.get() != 0) {
                        Data.livesCount.set(Data.livesCount.get() - 1);
                        this.pacMan.resetStatus();
                        for(int x = 0; x < Data.GHOST_NUMBER; ++x) {
                            this.ghosts[x].resetStatus();
                        }

                    } else {
                        for(int x = 0; x < Data.GHOST_NUMBER; ++x) {
                            this.ghosts[x].stop();
                        }
                        this.pacMan.stop();
                        this.messageLoseBox.setVisible(true);
                    }
                }
            }
        }

    }

    public void pacManEatsGhost(Ghost ghost) {
        ghost.stop();
        ghost.resetStatus();
        ghost.trapCounter = -10;
    }

    private class СontrolThread extends Thread {
        private KeyEvent event;

        public СontrolThread(KeyEvent event) {
            this.event = event;
        }
        public void run() {
            synchronized(this) {
                Maze.this.changeState(this.event);
            }
        }
    }
}
