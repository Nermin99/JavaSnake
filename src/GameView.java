import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GameView implements KeyListener{
    private JFrame window;
    private Canvas gameCanvas;
    private GameLogic gameLogic;
    private Graphics2D g;
    private BufferStrategy backBuffer;
    private int key;

    private static int canvasWidth = 800;
    private static int canvasHeight = 600;

    public static int columns = 36;
    public static int rows = columns * canvasHeight / canvasWidth;

    private int boxWidth = canvasWidth / columns;
    private int boxHeight = canvasHeight / rows;

    public GameView() {
        gameLogic = new GameLogic(this);

        window = new JFrame("Snake");
        window.addKeyListener(this);
        createWindow();

        gameLogic.loadObjects();
    }

    public void createWindow() {
        gameCanvas = new Canvas();
        gameCanvas.setFocusable(false);
        gameCanvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        window.add(gameCanvas);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameCanvas.createBufferStrategy(2);
        backBuffer = gameCanvas.getBufferStrategy();
    }

    public void render() {
        g = (Graphics2D)backBuffer.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Apple
        if (gameLogic.apple != null) {
            g.setColor(gameLogic.apple.color);
            g.fillRect(getXpx(gameLogic.apple.xPos), getYpx(gameLogic.apple.yPos), boxWidth, boxHeight);
        }

        // Snakehead
        /*g.setColor(Color.blue);
        g.fillRect(getXpx(gameLogic.snake.snakeList.get(0).xPos), getYpx(gameLogic.snake.snakeList.get(0).yPos), boxWidth, boxHeight);*/

        // Snakebody
        g.setColor(Color.green);
        for (int i = 0; i < gameLogic.snake.snakeList.size(); i++) {
            g.fillRect(getXpx(gameLogic.snake.snakeList.get(i).xPos), getYpx(gameLogic.snake.snakeList.get(i).yPos), boxWidth, boxHeight);
        }


        g.dispose();
        backBuffer.show();
    }

    /* Grid-funktioner */
    public int getXpx(int xPos) {
        return Math.round(xPos * gameCanvas.getWidth() / columns);
    }

    public int getYpx(int yPos) {
        return Math.round(yPos * gameCanvas.getHeight() / rows);
    }

    public int getXpos(int xPx){
        return 0;
    }

    public int getYpos(int yPx) {
        return 0;
    }


    /* Spelets tangentbordslyssnare */
    public int getKey() {
        return this.key;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new GameView();
    }
}

