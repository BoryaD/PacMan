package tritpo.menu;

import javafx.animation.ScaleTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tritpo.Data;

class LevelButton extends HBox {
    private int level;
    private Text text;

    public LevelButton(String name, int ghostLevel, Color colorOfButton) {
        this.level = ghostLevel;
        this.text = new Text(name);
        if (Data.GHOST_NUMBER == this.level) {
            this.text.setFill(colorOfButton);
        } else {
            this.text.setFill(Color.WHITE);
        }

        this.text.setFont(Data.PACKMAN_FONT_OPTIONS);
        this.getChildren().addAll(this.text);
        this.setOnMouseEntered((event) -> {
            ScaleTransition increase = new ScaleTransition(Duration.seconds(Data.TIME_OF_INCREASING), this.text);
            increase.setToX(Data.INCRECED_SIZE_OF_BUTTON);
            increase.setToY(Data.INCRECED_SIZE_OF_BUTTON);
            increase.play();
        });
        this.setOnMouseExited((event) -> {
            ScaleTransition decrease = new ScaleTransition(Duration.seconds(Data.TIME_OF_INCREASING), this.text);
            decrease.setFromX(Data.INCRECED_SIZE_OF_BUTTON);
            decrease.setFromY(Data.INCRECED_SIZE_OF_BUTTON);
            decrease.setToX(Data.NORMAL_SIZE_OF_BUTTON);
            decrease.setToY(Data.NORMAL_SIZE_OF_BUTTON);
            decrease.play();
        });
    }

    public void setColor(Color colorOfButton) {
        this.text.setFill(colorOfButton);
    }

    public void setLevel() {
        Data.GHOST_NUMBER = this.level;
    }
}
