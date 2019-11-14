package tritpo.gameLogic;

import tritpo.Data;

public class MoveDecision {
    public int x;
    public int y;
    public int score;

    public MoveDecision() {
    }

    public void evaluate(PacMan pacMan, boolean isHollow) {
        if (this.x >= 1 && this.y >= 1 && this.y < Data.Y_PIXELS-1 && this.x < Data.X_PIXELS-1) {
            int status = Data.getData(this.x, this.y);
            if (status == Data.MOVING) {
                this.score = -1;
            } else {
                int distance = Math.abs(this.x - pacMan.x) + Math.abs(this.y - pacMan.y);
                if (isHollow) {
                    this.score = Data.START_SCORE + distance;
                } else {
                    this.score = Data.START_SCORE - distance;
                }
            }
        } else {
            this.score = -1;
        }
    }
}

