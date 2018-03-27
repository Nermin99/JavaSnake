import java.awt.*;

public class SnakeBits {
    public int xPos;
    public int yPos;
    public Color color;

    public SnakeBits(int xPos, int yPos, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    public int getXpos() {
        return xPos;
    }

    public int getYpos() {
        return yPos;
    }

    public Color getColor() {
        return color;
    }
}
