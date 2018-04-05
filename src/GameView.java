import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * Klassen behandlar den visuella delen och gränsnittet av programmet.
 * Innehåller main-metoden för spelet där programmet startar.
 *
 * @author Nermin Skenderovic
 * @version 1.0
 * @since 2018-04-05
 */
public class GameView implements KeyListener{
    private int key;
    private Graphics2D g;
    private JFrame window;
    private Canvas gameCanvas;
    private GameLogic gameLogic;
    private BufferStrategy backBuffer;

    private static int canvasWidth = 800;  // 2 * 2 * 2 * 2 * 2 * 5 * 5
    private static int canvasHeight = 600; // 2 * 2 * 2 * 3 * 5 * 5

    /* columns måste vara en produkt av canvasWidth's primfaktorer för bästa upplevelse */
    public static int columns = 32; // 32 = 2 * 2 * 2 * 2 * 2 => 25.0px / ruta
    public static int rows = columns * canvasHeight / canvasWidth;

    private int squareWidth = canvasWidth / columns;
    private int squareHeight = canvasHeight / rows;

    /**
     * Konstruktorn instanserar GameLogic och bygger upp gränssnittet.
     */
    private GameView() {
        gameLogic = new GameLogic(this);

        window = new JFrame("Snake");
        window.addKeyListener(this);
        createWindow();

        gameLogic.loadObjects();
    }

    /**
     * Skapar självaste fönstret och canvasen till spelet.
     */
    private void createWindow() {
        gameCanvas = new Canvas();
        gameCanvas.setFocusable(false);
        gameCanvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        window.add(gameCanvas);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameCanvas.createBufferStrategy(2);
        backBuffer = gameCanvas.getBufferStrategy();

        // initiera långsam kod innan gameloop startar
        loadingScreen();
    }

    /**
     * Ritar ut en splashcreen sålänge den långsamma metoden g.drawString laddas in.
     */
    private void loadingScreen() {
        Image splashcreen = new ImageIcon(getClass().getResource("snek.jpg")).getImage();

        g = (Graphics2D)backBuffer.getDrawGraphics();
        g.drawImage(splashcreen, 0, 0, null);

        g.dispose();
        backBuffer.show();


        g = (Graphics2D)backBuffer.getDrawGraphics();
        g.drawString("", 0, 0);

        g.dispose();
        backBuffer.show();
    }

    /**
     * Renderar objekten på canvasen.
     * <p>
     * Ritar ut en svart bakgrund, det röda äpplet och den gröna ormen på skärmen.
     * Även användarens score och highscore visas i top vänstra respektive högra hörnen.
     */
    public void render() {
        g = (Graphics2D)backBuffer.getDrawGraphics();

        // Background
        g.setColor(Color.black);
        g.fillRect(0,0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Apple
        if (gameLogic.getApple() != null) {
            g.setColor(gameLogic.getApple().getColor());
            g.fillRect(getXpx(gameLogic.getApple().getXpos()), getYpx(gameLogic.getApple().getYpos()), squareWidth, squareHeight);
        }

        // Snake
        g.setColor(gameLogic.getSnake().getColor());
        for (int i = 0; i < gameLogic.getSnake().getSnakeList().size(); i++) {
            g.fillRect(getXpx(gameLogic.getSnake().getSnakeList().get(i).getXpos()), getYpx(gameLogic.getSnake().getSnakeList().get(i).getYpos()), squareWidth, squareHeight);
        }

        // Score
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Monaco", Font.BOLD, 24));
        g.setColor(Color.white);
        g.drawString("Score: " + gameLogic.getScore(), 2, 22);
        g.drawString("HighScore: " + gameLogic.getHighScore(), canvasWidth - g.getFontMetrics().stringWidth("HighScore: " + gameLogic.getHighScore()) - 2, 22);

        g.dispose();
        backBuffer.show();
    }

    /**
     * Konverterar x-positioner till kordinater i pixlar.
     *
     * @param xPos x-kordinaten i rutsystemet
     * @return x-kordinaten i px
     */
    private int getXpx(int xPos) {
        return Math.round(xPos * gameCanvas.getWidth() / columns);
    }

    /**
     * Konverterar y-positioner till kordinater i pixlar.
     *
     * @param yPos y-kordinaten i rutsystemet
     * @return y-kordinaten i px
     */
    private int getYpx(int yPos) {
        return Math.round(yPos * gameCanvas.getHeight() / rows);
    }

    /**
     * Returnerar vilken tangent som trycktes ner senast.
     *
     * @return nyckelkoden för tangenten
     */
    public int getKey() {
        return this.key;
    }

    /* Spelets tangentbordslyssnare */
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

