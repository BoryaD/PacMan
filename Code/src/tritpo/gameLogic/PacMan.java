package tritpo.gameLogic;

import java.util.Random;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tritpo.Data;


import static java.lang.System.exit;

public class PacMan extends Moving {
    public int dotEatenCount;
    public SimpleIntegerProperty score;
    private static final int[] ROTATION_DEGREE = new int[]{0, 90, 180, 270};
    private int keyboardBuffer;
    private final SimpleIntegerProperty currentDirection;

    public PacMan(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
        Image defaultImage = new Image("file:resources/images/left1.png");
        try {

            this.images = new Image[]{defaultImage, new Image("file:resources/images/left2.png"), defaultImage, new Image("file:resources/images/round.png")};
        }
        catch (NullPointerException ex) {
            exit(1);
        }
        this.dotEatenCount = 0;
        this.score = new SimpleIntegerProperty(0);
        this.currentDirection = new SimpleIntegerProperty(0);
        this.imageX = new SimpleIntegerProperty(Data.calcGridInt(x));
        this.imageY = new SimpleIntegerProperty(Data.calcGridInt(y));
        this.xDirection = -1;
        this.yDirection = 0;
        ImageView pacmanImage = new ImageView(defaultImage);
        pacmanImage.xProperty().bind(this.imageX.add(Data.IMAGE_OFFSET));
        pacmanImage.yProperty().bind(this.imageY.add(Data.IMAGE_OFFSET));
        pacmanImage.imageProperty().bind(this.imageBinding);
        IntegerBinding rotationBinding = new IntegerBinding() {
            {
                super.bind(PacMan.this.currentDirection);
            }
            protected int computeValue() {
                return PacMan.ROTATION_DEGREE[PacMan.this.currentDirection.get()];
            }
        };
        pacmanImage.rotateProperty().bind(rotationBinding);
        this.keyboardBuffer = -1;
        this.getChildren().add(pacmanImage);
    }

    private void autoMod() {
        Random rand = new Random();
        int nextX = this.xDirection + this.x;
        int nextY = this.yDirection + this.y;
        if (Data.getData(nextX, this.y) == 1) {
            if (rand.nextBoolean()) {
                this.keyboardBuffer = 3;
            } else {
                this.keyboardBuffer = 1;
            }
        }

        if (Data.getData(this.x, nextY) == 1) {
            if (rand.nextBoolean()) {
                this.keyboardBuffer = 0;
            } else {
                this.keyboardBuffer = 2;
            }
        }
    }

    private void moveHorizontally() {
        ++this.moveCounter;
        if (this.moveCounter < 4) {
            this.imageX.set(this.imageX.get() + this.xDirection * 4);
        } else {
            this.moveCounter = 0;
            this.x += this.xDirection;
            this.imageX.set(Data.calcGridInt(this.x));
            int nextX = this.xDirection + this.x;
            if (this.y == 14 && (nextX <= 1 || nextX >= 28)) {
                if (nextX < -1 && this.xDirection < 0) {
                    this.x = 29;
                    this.imageX.set(Data.calcGridInt(this.x));
                } else if (nextX > 30 && this.xDirection > 0) {
                    this.x = 0;
                    this.imageX.set(Data.calcGridInt(this.x));
                }
            } else if (Data.getData(nextX, this.y) == 1) {
                this.state = 0;
            }
        }
    }

    private void moveVertically() {
        ++this.moveCounter;
        if (this.moveCounter < 4) {
            this.imageY.set(this.imageY.get() + this.yDirection * 4);
        } else {
            this.moveCounter = 0;
            this.y += this.yDirection;
            this.imageY.set(Data.calcGridInt(this.y));
            int nextY = this.yDirection + this.y;
            if (Data.getData(this.x, nextY) == 1) {
                this.state = 0;
            }
        }
    }

    private void moveRight() {
        if (this.currentDirection.get() != 2) {
            int nextX = this.x + 1;
            if (nextX < 29) {
                if (Data.getData(nextX, this.y) != 1) {
                    this.xDirection = 1;
                    this.yDirection = 0;
                    this.keyboardBuffer = -1;
                    this.currentDirection.set(2);
                    this.state = 1;
                }
            }
        }
    }

    private void moveLeft() {
        if (this.currentDirection.get() != 0) {
            int nextX = this.x - 1;
            if (nextX > 1) {
                if (Data.getData(nextX, this.y) != 1) {
                    this.xDirection = -1;
                    this.yDirection = 0;
                    this.keyboardBuffer = -1;
                    this.currentDirection.set(0);
                    this.state = 1;
                }
            }
        }
    }

    private void moveUp() {
        if (this.currentDirection.get() != 1) {
            int nextY = this.y - 1;
            if (nextY > 1) {
                if (Data.getData(this.x, nextY) != 1) {
                    this.xDirection = 0;
                    this.yDirection = -1;
                    this.keyboardBuffer = -1;
                    this.currentDirection.set(1);
                    this.state = 1;
                }
            }
        }
    }

    private void moveDown() {
        if (this.currentDirection.get() != 3) {
            int nextY = this.y + 1;
            if (nextY < 31) {
                if (Data.getData(this.x, nextY) != 1) {
                    this.xDirection = 0;
                    this.yDirection = 1;
                    this.keyboardBuffer = -1;
                    this.currentDirection.set(3);
                    this.state = 1;
                }
            }
        }
    }

    private void handleKeyboardInput() {
        if (this.keyboardBuffer >= 0) {
            if (this.keyboardBuffer == 0) {
                this.moveLeft();
            } else if (this.keyboardBuffer == 2) {
                this.moveRight();
            } else if (this.keyboardBuffer == 1) {
                this.moveUp();
            } else if (this.keyboardBuffer == 3) {
                this.moveDown();
            }
        }
    }

    public void setKeyboardBuffer(int keyboard) {
        this.keyboardBuffer = keyboard;
    }

    public int getKeyboardBuffer() {
        return this.keyboardBuffer;
    }

    private void updateScore() {
        if (this.y != 14 || this.x > 0 && this.x < 29) {
            Dot dot = (Dot)Data.getDot(this.x, this.y);
            if (dot != null && dot.isVisible()) {
                this.score.set(this.score.get() + 10);
                dot.setVisible(false);
                Data.setData(this.x, this.y, 0);
                ++this.dotEatenCount;
                if (dot.dotType == 3) {
                    this.maze.makeGhostsHollow();
                }

                if (this.dotEatenCount >= Data.getDotTotal()) {

                    for(int y = 0; y < Data.GHOST_NUMBER; ++y) {
                        maze.ghosts[y].stop();
                    }

                    this.stop();
                    this.maze.messageWinBox.setVisible(true);
                }
            }
        }
    }

    public void moveOneStep() {

        if (Data.autoMod) {
            this.autoMod();
        }

        if (this.currentImage.get() == 0) {
            this.handleKeyboardInput();
        }

        if (this.state == 1) {
            if (this.xDirection != 0) {
                this.moveHorizontally();
            }

            if (this.yDirection != 0) {
                this.moveVertically();
            }

            if (this.currentImage.get() < 3) {
                this.currentImage.set(this.currentImage.get() + 1);
            } else {
                this.currentImage.set(0);
                this.updateScore();
            }
        }

        this.maze.pacManMeetsGhosts();
    }

    public void resetStatus() {
        if (Data.livesCount.get() == 2) {
            this.dotEatenCount = 0;
            this.score.set(0);
        }
        this.state = 1;
        this.currentDirection.set(0);
        this.xDirection = -1;
        this.yDirection = 0;
        this.keyboardBuffer = -1;
        this.currentImage.set(0);
        this.moveCounter = 0;
        this.x = 15;
        this.y = 24;
        this.imageX.set(Data.calcGridInt(this.x));
        this.imageY.set(Data.calcGridInt(this.y));
        this.setVisible(true);
        this.start();
    }
}
