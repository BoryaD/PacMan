package tritpo.gameLogic;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tritpo.Data;

public class WallBlackRectangle extends Parent {
    public WallBlackRectangle(float x1, float y1, float x2, float y2) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX((double)(Data.calcGrid(x1) + 2.0F));
        rectangle.setY((double)(Data.calcGrid(y1) + 2.0F));
        rectangle.setWidth((double)(Data.PIXEL_SIZE * (x2 - x1) - 4.0F));
        rectangle.setHeight((double)(Data.PIXEL_SIZE * (y2 - y1) - 4.0F));
        rectangle.setStrokeWidth(Data.RECTANGLE_STROKE_WIDTH);
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(Data.RECTANNGLE_ARC_WIDTH);
        rectangle.setArcHeight(Data.RECTANNGLE_ARC_WIDTH);
        rectangle.setCache(true);
        this.getChildren().add(rectangle);
    }
}

