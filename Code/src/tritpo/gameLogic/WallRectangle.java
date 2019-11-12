package tritpo.gameLogic;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tritpo.Data;

public class WallRectangle extends Parent {
    public WallRectangle(float x1, float y1, float x2, float y2) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX((double)Data.calcGrid(x1));
        rectangle.setY((double)Data.calcGrid(y1));
        rectangle.setWidth((double)(Data.calcGrid(x2) - Data.calcGrid(x1)));
        rectangle.setHeight((double)(Data.calcGrid(y2) - Data.calcGrid(y1)));
        rectangle.setStrokeWidth(Data.RECTANGLE_STROKE_WIDTH);
        rectangle.setStroke(Color.BLUE);
        rectangle.setArcWidth(4*Data.RECTANNGLE_ARC_WIDTH);
        rectangle.setArcHeight(4*Data.RECTANNGLE_ARC_WIDTH);
        rectangle.setCache(true);
        this.getChildren().add(rectangle);
        Data.setBlockMazeData(Math.round(x1), Math.round(y1), Math.round(x2), Math.round(y2));
    }
}