package tritpo.gameLogic;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tritpo.menu.MenuButton;
import tritpo.Data;

public class MessageBox extends Group {
    public MessageBox(Maze maze, String messegeType) {
        Rectangle rectMessage;
        DropShadow shadow;
        Text textMessage;
        VBox buttonsBox;
        MenuButton restartButton;
        MenuButton exitButton;
        if (messegeType == Data.PAUSE) {
            rectMessage = new Rectangle((double)Data.calcGrid(5.0F), (double)Data.calcGrid(11.0F), 304.0D, 208.0D);
            rectMessage.setStroke(Color.WHITE);
            rectMessage.setStrokeWidth(5.0D);
            rectMessage.setFill(Color.CYAN);
            rectMessage.setOpacity(0.75D);
            rectMessage.setArcWidth(25.0D);
            rectMessage.setArcHeight(25.0D);
            shadow = new DropShadow();
            shadow.setColor(Color.WHITE);
            shadow.setRadius(20.0D);
            rectMessage.setEffect(shadow);
            this.getChildren().add(rectMessage);
            textMessage = new Text((double)Data.calcGrid(6.2F), (double)Data.calcGrid(15.3F), messegeType);
            textMessage.setFill(Color.WHITE);
            textMessage.setFont(Data.PACKMAN_FONT_MESSAGE);
            buttonsBox = new VBox(20.0D);
            restartButton = new MenuButton(Data.CONTINUE);
            exitButton = new MenuButton(Data.EXIT);
            buttonsBox.getChildren().addAll(new Node[]{restartButton, exitButton});
            buttonsBox.setLayoutY((double)Data.calcGrid(17.5F));
            buttonsBox.setLayoutX((double)Data.calcGrid(8.7F));
            restartButton.setOnMouseClicked((event) -> {

                this.setVisible(false);
            });
            exitButton.setOnMouseClicked((event) -> {
                maze.setMenu();
            });
            this.getChildren().addAll(new Node[]{textMessage, buttonsBox});
        }

        if (messegeType == Data.PK_TO_START) {
            rectMessage = new Rectangle((double)Data.calcGrid(3.5F), (double)Data.calcGrid(12.0F), 360.0D, 96.0D);
            rectMessage.setStroke(Color.WHITE);
            rectMessage.setStrokeWidth(5.0D);
            rectMessage.setFill(Color.CYAN);
            rectMessage.setOpacity(0.75D);
            rectMessage.setArcWidth(25.0D);
            rectMessage.setArcHeight(25.0D);
            shadow = new DropShadow();
            shadow.setColor(Color.WHITE);
            shadow.setRadius(20.0D);
            rectMessage.setEffect(shadow);
            this.getChildren().add(rectMessage);
            textMessage = new Text((double)Data.calcGrid(4.5F), (double)Data.calcGrid(15.0F), messegeType);
            textMessage.setFill(Color.WHITE);
            textMessage.setFont(Data.PACKMAN_FONT_MENU);
            this.getChildren().addAll(new Node[]{textMessage});
        }

        if (messegeType == Data.YOU_LOSE || messegeType == Data.YOU_WIN) {
            rectMessage = new Rectangle((double)Data.calcGrid(3.0F), (double)Data.calcGrid(11.0F), 368.0D, 208.0D);
            rectMessage.setStroke(Color.WHITE);
            rectMessage.setStrokeWidth(5.0D);
            rectMessage.setFill(Color.CYAN);
            rectMessage.setOpacity(0.75D);
            rectMessage.setArcWidth(25.0D);
            rectMessage.setArcHeight(25.0D);
            shadow = new DropShadow();
            shadow.setColor(Color.WHITE);
            shadow.setRadius(20.0D);
            rectMessage.setEffect(shadow);
            this.getChildren().add(rectMessage);
            textMessage = new Text((double)Data.calcGrid(4.5F), (double)Data.calcGrid(15.3F), messegeType);
            textMessage.setFill(Color.WHITE);
            textMessage.setFont(Data.PACKMAN_FONT_MENU);
            buttonsBox = new VBox(20.0D);
            restartButton = new MenuButton(Data.RESTART);
            exitButton = new MenuButton(Data.EXIT);
            buttonsBox.getChildren().addAll(new Node[]{restartButton, exitButton});
            buttonsBox.setLayoutY((double)Data.calcGrid(17.5F));
            buttonsBox.setLayoutX((double)Data.calcGrid(8.7F));
            restartButton.setOnMouseClicked((event) -> {
                Data.livesCount.set(2);

                this.setVisible(false);
            });
            exitButton.setOnMouseClicked((event) -> {
                maze.setMenu();
            });
            this.getChildren().addAll(new Node[]{textMessage, buttonsBox});
        }

    }
}

