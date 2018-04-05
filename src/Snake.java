import java.awt.*;
import java.util.ArrayList;

/**
 * Klassen är modellen för snake-objektet.
 *
 * @author Nermin Skenderovic
 * @version 1.0
 * @since 2018-04-05
 */
public class Snake {
    private int dx;
    private int dy;
    private Color color;
    private ArrayList<SnakeBits> snakeList = new ArrayList<>(3);

    /**
     * Konstruktorn för denna klass Snake.
     * <p>
     * Konstruktorn tar emot och sätter kordinater, riktning, längd och färg för ormen.
     * Skickar därefter med längden och kordinaterna till nästa metod createSnake.
     *
     * @param dx riktning i x-led. Tillåtna värden: -1 (vänster), 0 (still), 1 (höger)
     * @param dy riktning i y-led. Tillåtna värden: -1 (upp), 0 (still), 1 (ner)
     * @param length startlängden för ormen (i rutor)
     * @param defX startkordinaten i x-led för huvudet
     * @param defY startkordinaten i y-led för huvudet
     * @param color färgen på ormen
     */
    public Snake(int dx, int dy, int length, int defX, int defY, Color color) {
        this.dx = dx;
        this.dy = dy;
        this.color = color;

        createSnake(length, defX, defY);
    }

    /**
     * Metoden skapar ormen med hjälp av längden och kordinaterna.
     *
     * @param length längden för ormen
     * @param defX kordinaten i x-led för huvudet
     * @param defY kordinaten i y-led för huvudet
     */
    public void createSnake(int length, int defX, int defY) {
        for (int i = defX; i < defX + length; i++) {
            snakeList.add(new SnakeBits(i, defY));
        }
    }

    /**
     * Flyttar ormen frammåt i färdriktningen.
     * <p>
     * Ormen flyttas genom att svansen tas bort och läggs till framför huvudet,
     * vilket gör den till det nya huvudet.
     * Om användaren har ätit ett äpple behövs inte svansen tas bort som gör att
     * ormen blir längre och rör sig frammåt.
     *
     * @param appleEaten om äpplet ätits eller inte (sant eller falskt)
     */
    public void move(boolean appleEaten) {
        if (appleEaten == false) {
            snakeList.remove(snakeList.size()-1);
        }
        snakeList.add(0, new SnakeBits(snakeList.get(0).getXpos() + dx, snakeList.get(0).getYpos() + dy));
    }

    /**
     * Returnerar ormens färdriktning i x-led.
     *
     * @return riktning i x-led. (-1, 0, 1)
     */
    public int getDx() {
        return this.dx;
    }

    /**
     * Returnerar ormens färdriktning i y-led.
     *
     * @return riktning i y-led. (-1, 0, 1)
     */
    public int getDy() {
        return this.dy;
    }

    /**
     * Sätter ormens färdriktning i x-led.
     *
     * @param dx vilken riktning i x-led. (-1, 0, 1)
     */
    public void setDirectionX(int dx) {
        this.dx = dx;
    }

    /**
     * Sätter ormens färdriktning i y-led.
     *
     * @param dy vilken riktning i y-led. (-1, 0, 1)
     */
    public void setDirectionY(int dy) {
        this.dy = dy;
    }

    /**
     * Returnerar färgen på ormen.
     *
     * @return vilken färg ormen har
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returnerar ArrayList-objektet som ormen består av.
     *
     * @return referensen till snakeList-objektet
     */
    public ArrayList<SnakeBits> getSnakeList() {
        return this.snakeList;
    }
}
