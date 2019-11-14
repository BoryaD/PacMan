package tritpo.gameLogic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tritpo.Data;

import static java.lang.System.exit;

public class Ghost extends Moving {
    private final PacMan pacMan;
    private static Image HOLLOW_IMAGE1;
    private static Image HOLLOW_IMAGE2;
    private static Image HOLLOW_IMAGE3;

    private static final Image[] HOLLOW_IMG;
    private static final Image[] FLASH_HOLLOW_IMG;
    private int hollowCounter;
    private final Image[] defaultImg;
    private final int initialLocationX;
    private final int initialLocationY;
    private final int initialDirectionX;
    private final int initialDirectionY;
    private final int trapTime;
    public int trapCounter;

    public boolean isHollow;

    static {

        try {
            HOLLOW_IMAGE1 = new Image("file:resources/images/ghosthollow2.png");
            HOLLOW_IMAGE2 = new Image("file:resources/images/ghosthollow3.png");
            HOLLOW_IMAGE3 = new Image("file:resources/images/ghosthollow1.png");
        } catch (NullPointerException ex) {
            exit(1);
        }

        HOLLOW_IMG = new Image[]{HOLLOW_IMAGE1, HOLLOW_IMAGE2, HOLLOW_IMAGE1, HOLLOW_IMAGE2};
        FLASH_HOLLOW_IMG = new Image[]{HOLLOW_IMAGE1, HOLLOW_IMAGE3, HOLLOW_IMAGE1, HOLLOW_IMAGE3};
    }

    public Ghost(Image defaultImage1, Image defaultImage2, Maze maze, PacMan pacMan, int x, int y, int xDirection, int yDirection, int trapTime) {
        this.maze = maze;
        this.pacMan = pacMan;
        this.x = x;
        this.y = y;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.trapTime = trapTime;
        this.defaultImg = new Image[]{defaultImage1, defaultImage2, defaultImage1, defaultImage2};
        this.images = this.defaultImg;
        this.isHollow = false;
        this.trapCounter = 0;
        this.initialLocationX = x;
        this.initialLocationY = y;
        this.initialDirectionX = xDirection;
        this.initialDirectionY = yDirection;
        this.imageX = new SimpleIntegerProperty(Data.calcGridInt(x));
        this.imageY = new SimpleIntegerProperty(Data.calcGridInt(y));
        ImageView ghostNode = new ImageView(defaultImage1);
        ghostNode.xProperty().bind(this.imageX.add(Data.IMAGE_OFFSET));
        ghostNode.yProperty().bind(this.imageY.add(Data.IMAGE_OFFSET));
        ghostNode.imageProperty().bind(this.imageBinding);
        ghostNode.setCache(true);
        this.getChildren().add(ghostNode);
    }

    public void resetStatus() {
        this.x = this.initialLocationX;
        this.y = this.initialLocationY;
        this.xDirection = this.initialDirectionX;
        this.yDirection = this.initialDirectionY;
        this.isHollow = false;
        this.moveCounter = 0;
        this.trapCounter = 0;
        this.currentImage.set(0);
        this.imageX.set(Data.calcGridInt(this.x));
        this.imageY.set(Data.calcGridInt(this.y));
        this.images = this.defaultImg;
        this.state = Data.TRAPPED;
        this.setVisible(true);
        this.start();
    }

    public void changeToHollowGhost() {
        this.hollowCounter = 0;
        this.isHollow = true;
        this.images = HOLLOW_IMG;
    }

    private void changeDirectionXtoY(boolean mustChange) {
        if (mustChange || Math.random() <= Data.CHANGE_FACTOR) {
            MoveDecision goUp = new MoveDecision();
            goUp.x = this.x;
            goUp.y = this.y - 1;
            MoveDecision goDown = new MoveDecision();
            goDown.x = this.x;
            goDown.y = this.y + 1;
            goUp.evaluate(this.pacMan, this.isHollow);
            goDown.evaluate(this.pacMan, this.isHollow);
            if (goUp.score >= 0 || goDown.score >= 0) {
                MoveDecision continueGo = new MoveDecision();
                continueGo.x = this.x + this.xDirection;
                continueGo.y = this.y;
                continueGo.evaluate(this.pacMan, this.isHollow);
                if (continueGo.score > 0 && continueGo.score > goUp.score && continueGo.score > goDown.score) {
                } else {
                    int decision = -1;
                    if (goUp.score < 0) {
                        decision = 1;
                    } else if (goDown.score > 0) {

                        if (goDown.score > goUp.score) {
                            decision = 1;
                        } else if (Math.random() > Data.CHANGE_FACTOR_2) {
                            decision = 1;
                        }
                    }
                    this.yDirection = decision;
                    this.xDirection = 0;
                }
            }
        }
    }

    private void changeDirectionYtoX(boolean mustChange) {
        if (mustChange || Math.random() <= 0.75D) {
            MoveDecision goLeft = new MoveDecision();
            goLeft.x = this.x - 1;
            goLeft.y = this.y;
            MoveDecision goRight = new MoveDecision();
            goRight.x = this.x + 1;
            goRight.y = this.y;
            goLeft.evaluate(this.pacMan, this.isHollow);
            goRight.evaluate(this.pacMan, this.isHollow);
            if (goLeft.score >= 0 || goRight.score >= 0) {

                MoveDecision continueGo = new MoveDecision();
                continueGo.x = this.x;
                continueGo.y = this.y + this.yDirection;
                continueGo.evaluate(this.pacMan, this.isHollow);
                if (continueGo.score > 0 && continueGo.score > goLeft.score && continueGo.score > goRight.score) {
                } else {
                    int decision = -1;
                    if (goLeft.score < 0) {
                        decision = 1;
                    } else if (goRight.score > 0) {
                        if (goRight.score > goLeft.score) {
                            decision = 1;
                        } else if (Math.random() > 0.5D) {
                            decision = 1;
                        }
                    }

                    this.xDirection = decision;
                    this.yDirection = 0;
                }
            }
        }
    }

    private void moveHorizontally() {
        ++this.moveCounter;
        if (this.moveCounter > 3) {
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
            } else if ((nextX < 0 || nextX > 29)) {
                this.changeDirectionXtoY(true);
            } else if (Data.getData(nextX, this.y) == 1) {
                this.changeDirectionXtoY(true);
            } else {
                this.changeDirectionXtoY(false);
            }
        } else {
            this.imageX.set(this.imageX.get() + this.xDirection * 4);
        }

    }

    private void moveVertically() {
        ++this.moveCounter;
        if (this.moveCounter > 3) {
            this.moveCounter = 0;
            this.y += this.yDirection;
            this.imageY.set(Data.calcGridInt(this.y));
            int nextY = this.yDirection + this.y;
            if (nextY >= 0 && nextY <= 31) {
                if (Data.getData(this.x, nextY) == 1) {
                    this.changeDirectionYtoX(true);
                } else {
                    this.changeDirectionYtoX(false);
                }
            } else {
                this.changeDirectionYtoX(true);
            }

        } else {
            this.imageY.set(this.imageY.get() + this.yDirection * 4);
        }

    }

    private void moveHorizontallyInCage() {
        ++this.moveCounter;
        if (this.moveCounter > 3) {
            this.moveCounter = 0;
            this.x += this.xDirection;
            this.imageX.set(Data.calcGridInt(this.x));
            int nextX = this.xDirection + this.x;
            if (nextX < 12) {
                this.xDirection = 0;
                this.yDirection = 1;
            } else if (nextX > 17) {
                this.xDirection = 0;
                this.yDirection = -1;
            }
        } else {
            this.imageX.set(this.imageX.get() + this.xDirection * 4);
        }

    }

    private void moveVerticallyInCage() {
        ++this.moveCounter;
        if (this.moveCounter > 3) {
            this.moveCounter = 0;
            this.y += this.yDirection;
            this.imageY.set(Data.calcGridInt(this.y) + 8);
            int nextY = this.yDirection + this.y;
            if (nextY < 13) {
                this.yDirection = 0;
                this.xDirection = -1;
            } else if (nextY > 15) {
                this.yDirection = 0;
                this.xDirection = 1;
            }
        } else {
            this.imageY.set(this.imageY.get() + this.yDirection * 4);
        }

    }

    public void moveOneStep() {
        if (this.maze.gamePaused.get()) {
            if (!this.isPaused()) {
                this.timeline.pause();
            }

        } else {
            if (this.state == Data.MOVING || this.state == Data.TRAPPED) {
                if (this.xDirection != 0) {
                    if (this.state == Data.MOVING) {
                        this.moveHorizontally();
                    } else {
                        this.moveHorizontallyInCage();
                    }
                } else if (this.yDirection != 0) {
                    if (this.state == Data.MOVING) {
                        this.moveVertically();
                    } else {
                        this.moveVerticallyInCage();
                    }
                }

                if (this.currentImage.get() < 3) {
                    this.currentImage.set(this.currentImage.get() + 1);
                } else {
                    this.currentImage.set(0);
                    if (this.state == Data.TRAPPED) {
                        ++this.trapCounter;
                        if (this.trapCounter > this.trapTime && this.x == 14 && this.y == 13) {
                            this.y = 12;
                            this.xDirection = 0;
                            this.yDirection = -1;
                            this.state = 1;
                        }
                    }
                }
            }

            if (this.isHollow) {
                ++this.hollowCounter;
                if (this.hollowCounter == Data.FLASH_HOLLOW_MAX_TIME) {
                    this.images = FLASH_HOLLOW_IMG;
                } else if (this.hollowCounter > Data.HOLLOW_MAX_TIME) {
                    this.isHollow = false;
                    this.images = this.defaultImg;
                    this.timeline.stop();
                    this.timeline.setRate(1.0D);
                    this.timeline.play();
                }
            }

        }
    }
}
