package tritpo.menu;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import tritpo.*;

public class MyToggleButton extends Parent {

    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25D));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25D));
    private ParallelTransition animation;

    public MyToggleButton() {
        this.animation = new ParallelTransition(new Animation[]{this.translateAnimation, this.fillAnimation});
        Rectangle background = new Rectangle(Data.WIDTH_OF_TOGGLE_BUTTON, Data.HEIGHT_OF_TOGGLE_BUTTON);
        background.setArcWidth(Data.HEIGHT_OF_TOGGLE_BUTTON);
        background.setArcHeight(Data.HEIGHT_OF_TOGGLE_BUTTON);
        background.setFill(Data.autoMod ? Color.GREEN : Color.GRAY);
        background.setStroke(Color.LIGHTGRAY);
        Circle trigger = new Circle(Data.HEIGHT_OF_TOGGLE_BUTTON/2);
        trigger.setCenterX(Data.HEIGHT_OF_TOGGLE_BUTTON/2);
        trigger.setCenterY(Data.HEIGHT_OF_TOGGLE_BUTTON/2);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);
        if (Data.autoMod) {
            trigger.setTranslateX(Data.HEIGHT_OF_TOGGLE_BUTTON/2);
        }

        this.translateAnimation.setNode(trigger);
        this.fillAnimation.setShape(background);
        this.getChildren().addAll(new Node[]{background, trigger});
        this.setOnMouseClicked((event) -> {
            this.switchedOn(!Data.autoMod);
        });
    }

    public void switchedOn(boolean state) {
        Data.autoMod = state;
        this.translateAnimation.setToX((double)(state ? Data.HEIGHT_OF_TOGGLE_BUTTON : 0));
        this.fillAnimation.setFromValue(state ? Color.GRAY : Color.GREEN);
        this.fillAnimation.setToValue(state ? Color.GREEN : Color.GRAY);
        this.animation.play();
    }
}
