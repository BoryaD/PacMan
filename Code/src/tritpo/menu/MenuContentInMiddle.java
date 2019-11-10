package tritpo.menu;

import javafx.animation.FadeTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tritpo.Data;

class MenuContentInMiddle extends Pane {
    public MenuContentInMiddle(String name) {
        FadeTransition fade = new FadeTransition(Duration.seconds(Data.TIME_OF_FADING), this);
        fade.setFromValue(Data.START_FADE_VALUE);
        fade.setToValue(Data.FINAL_FADE_VALUE);
        fade.play();
        Reflection reflection = new Reflection();
        reflection.setFraction(Data.REFLECTION_VALUE);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.YELLOW);
        Text text = new Text(name);
        text.setEffect(reflection);
        text.setFill(Color.YELLOW);
        text.setFont(Data.PACKMAN_FONT_TITLE);
        this.getChildren().addAll(text);
        this.setOnMouseEntered((event) -> {
            this.setEffect(shadow);
        });
        this.setOnMouseExited((event) -> {
            this.setEffect(null);
        });
        this.setOnMouseClicked((event) -> {
            fade.setFromValue(Data.START_FADE_VALUE);
            fade.play();
        });
    }
}