import java.awt.*;

public class Apple {
    public int xPos;
    public int yPos;
    public boolean active;
    public Color color = Color.red;

    public Apple() {
        xPos = (int) (Math.random() * GameView.columns);
        yPos = (int) (Math.random() * GameView.rows);
    }

    public int getXpos() {
        return xPos;
    }

    public int getYpos() {
        return yPos;
    }

    public boolean isActive() {
        return active;
    }

    public Color getColor() {
        return this.color;
    }
}
