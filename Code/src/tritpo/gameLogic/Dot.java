package tritpo.gameLogic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tritpo.Data;

public class Dot extends Parent {
    public int dotType;
    private IntegerProperty radius;

    public Dot(int x, int y, int dotType) {
        this.dotType = dotType;
        if (dotType == 3) {
            this.radius = new SimpleIntegerProperty(Data.BIG_DOT_RADIUS);
        } else {
            this.radius = new SimpleIntegerProperty(Data.LITTLE_DOT_RADIUS);
        }

        Circle circle = new Circle((double)x, (double)y, (double)this.radius.intValue(), Color.YELLOW);
        circle.radiusProperty().bind(this.radius);
        this.getChildren().add(circle);
    }
}
