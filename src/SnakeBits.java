/**
 * Klassen fungerar som beståndsdelarna till hur ormen är uppbygd.
 * Ormen är uppbygd av flera SnakeBits-objekt.
 *
 * @author Nermin Skenderovic
 * @version 1.0
 * @since 2018-04-05
 */
public class SnakeBits {
    private int xPos;
    private int yPos;

    /**
     * Konstruktorn, som tar emot kordinaterna för biten.
     *
     * @param xPos x-kordinaten för biten
     * @param yPos y-kordinaten för biten
     */
    public SnakeBits(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Returnerar x-kordinaten för biten.
     *
     * @return nuvarande x-kordinaten
     */
    public int getXpos() {
        return xPos;
    }

    /**
     * Returnerar y-kordinaten för biten.
     *
     * @return nuvarande y-kordinaten
     */
    public int getYpos() {
        return yPos;
    }

    /**
     * Sätter bitens x-kordinat.
     *
     * @param xPos kordinaten i x-led
     */
    public void setXpos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sätter bitens y-kordinat.
     *
     * @param yPos kordinaten i y-led
     */
    public void setYpos(int yPos) {
        this.yPos = yPos;
    }
}
