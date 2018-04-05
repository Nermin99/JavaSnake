import java.awt.*;

/**
 * Klassen fungerar som äpplet i spelet som, om man äter, man blir längre av.
 *
 * @author Nermin Skenderovic
 * @version 1.0
 * @since 2018-04-05
 */
public class Apple {
    private int yPos;
    private int xPos;
    private Color color = Color.red;

    /**
     * Konstruktorn; skapar slumpmässiga kordinater till äpplet på canvasen.
     *
     * @see GameView
     */
    public Apple() {
        xPos = (int) (Math.random() * GameView.columns);
        yPos = (int) (Math.random() * GameView.rows);
    }

    /**
     * Returnerar x-kordinaten för äpplet.
     *
     * @return x-kordinaten
     */
    public int getXpos() {
        return xPos;
    }

    /**
     * Returnerar y-kordinaten för äpplet.
     *
     * @return y-kordinaten
     */
    public int getYpos() {
        return yPos;
    }

    /**
     * Returnerar färgen på äpplet.
     *
     * @return vilken färg äpplet har
     */
    public Color getColor() {
        return this.color;
    }
}
