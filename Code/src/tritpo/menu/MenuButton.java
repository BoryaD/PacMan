package tritpo.menu;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tritpo.Data;

public class MenuButton extends HBox {
    public MenuButton(String name) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Data.PACKMAN_FONT_MENU);
        text.setEffect(shadow);
        this.getChildren().addAll(text);
        this.setAlignment(Pos.CENTER);
        this.setOnMouseEntered((event) -> {
            text.setFill(Color.WHITE);
            shadow.setColor(Color.WHITE);
            shadow.setRadius(Data.RADIUS_OF_SHADOW);
            ScaleTransition increase = new ScaleTransition(Duration.seconds(Data.TIME_OF_INCREASING), text);
            increase.setToX(Data.INCRECED_SIZE_OF_BUTTON);
            increase.setToY(Data.INCRECED_SIZE_OF_BUTTON);
            increase.play();
        });
        this.setOnMouseExited((event) -> {
            ScaleTransition decrease = new ScaleTransition(Duration.seconds(Data.TIME_OF_INCREASING), text);
            decrease.setFromX(Data.INCRECED_SIZE_OF_BUTTON);
            decrease.setFromY(Data.INCRECED_SIZE_OF_BUTTON);
            decrease.setToX(Data.NORMAL_SIZE_OF_BUTTON);
            decrease.setToY(Data.NORMAL_SIZE_OF_BUTTON);
            decrease.play();
            shadow.setColor(Color.BLACK);
        });
        this.setOnMousePressed((event) -> {
            ScaleTransition decrease = new ScaleTransition(Duration.seconds(0.001D), text);
            decrease.setFromX(Data.INCRECED_SIZE_OF_BUTTON);
            decrease.setFromY(Data.INCRECED_SIZE_OF_BUTTON);
            decrease.setToX(Data.NORMAL_SIZE_OF_BUTTON);
            decrease.setToY(Data.NORMAL_SIZE_OF_BUTTON);
            decrease.play();
        });
    }
}