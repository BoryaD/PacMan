package tritpo.gameLogic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tritpo.Data;
import tritpo.menu.Menu;

public class Maze extends Parent {


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
        this.group = new Group();
        this.gamePaused = new SimpleBooleanProperty(false);
        this.waitForStart = new SimpleBooleanProperty(true);
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
    }

    public void setMenu() {
        Data.content.getChildren().clear();
        Data.content.getChildren().add(Menu.getMainMenu());
        Menu.getMainMenu().requestFocus();
    }

}
