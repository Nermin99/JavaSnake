import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private Image img;
    private int dx;
    private int dy;

    public ArrayList<SnakeBits> snakeList = new ArrayList<>(3);

    public Snake(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void extend(int xPos, int yPos, Color color) {
        snakeList.add(new SnakeBits(xPos, yPos, color));
    }

    public void draw() {

    }

    public void move(boolean appleEaten) {
        if (appleEaten == false) {
            snakeList.remove(snakeList.size()-1);
        }
        snakeList.add(0, new SnakeBits(snakeList.get(0).xPos + dx, snakeList.get(0).yPos + dy, Color.green));
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDirectionX(int dx) {
        this.dx = dx;
    }

    public void setDirectionY(int dy) {
        this.dy = dy;
    }

    public ArrayList<SnakeBits> getSnakeList() {
        return this.snakeList;
    }
}
