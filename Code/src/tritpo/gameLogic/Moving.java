package tritpo.gameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tritpo.Data;

public abstract class Moving extends Parent {

    protected Maze maze;
    protected int state;
    protected IntegerProperty currentImage = new SimpleIntegerProperty(0);
    protected Image[] images;
    protected int moveCounter;
    protected int x;
    protected int y;
    public IntegerProperty imageX;
    public IntegerProperty imageY;
    protected int xDirection;
    protected int yDirection;
    protected Timeline timeline = this.createTimeline();

    protected ObjectBinding imageBinding = new ObjectBinding() {
        {
            super.bind(Moving.this.currentImage);
        }
        protected Image computeValue() {
            return Moving.this.images[Moving.this.currentImage.get()];
        }
    };

    public abstract void moveOneStep();

    private Timeline createTimeline() {
        this.timeline = new Timeline();
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis(Data.DURATION_MILLS), (event) ->{
            Moving.this.moveOneStep();
        });
        this.timeline.getKeyFrames().add(kf);
        return this.timeline;
    }

    public void stop() {
        this.timeline.stop();
    }

    public void pause() {
        this.timeline.pause();
    }

    public void start() {
        this.timeline.play();
    }

    public boolean isPaused() {
        return this.timeline.getStatus() == Status.PAUSED;
    }
}
