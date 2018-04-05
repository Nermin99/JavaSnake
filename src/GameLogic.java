import java.awt.*;

/**
 * Klassen behandlar den logiska sidan av programmet och sköter
 * majoriteten av interaktionen mellan model och view.
 *
 * @author Nermin Skenderovic
 * @version 1.0
 * @since 2018-04-05
 */
public class GameLogic {
    private Snake snake;
    private Apple apple;
    private GameView gameView;
    private int score, highScore = 0;
    private int fps = 15;
    private int baseLength = 3;
    private Color color = Color.green;

    /**
     * Konstruktor med referens till view.
     * <p>
     * När klassen blir instanserad får den även med instansen av view som
     * underlättar kommunikationen sinsemellan.
     *
     * @param gameView referensen till GameView
     */
    public GameLogic(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Laddar in och startar spelet.
     * <p>
     * Initierar ormen med dess standard kordinater och riktning.
     * Därefter sätts spelets gameloop igång.
     */
    public void loadObjects() {
        int defXpos = (gameView.columns - baseLength) / 2;
        int defYpos = gameView.rows / 2;

        int defDx = -1;
        int defDy = 0;

        snake = new Snake(defDx, defDy, baseLength, defXpos, defYpos, color);

        run();
    }

    /**
     * Uppdaterar ormens egenskaper.
     */
    private void update() {
        updateDirection();

        trailEaten();

        highScore = (score > highScore) ? score : highScore;

        snake.move(appleEaten());

        wrapBorder();
    }

    /**
     * Gameloop; uppdaterar och renderar med 15 uppdateringar per sekund.
     */
    private void run() {
        long lastUpdateTime = System.nanoTime();
        int updateTime = (int)(1000000000.0/fps);

        while (true) {
            long deltaTime = System.nanoTime() - lastUpdateTime;

            if (deltaTime >= updateTime) {
                lastUpdateTime = System.nanoTime();
                update();
                gameView.render();
            }
        }
    }

    /**
     * Uppdaterar färdrikntingen på ormen beroende på vilken tangent som trycks ner.
     */
    private void updateDirection() {
        switch (gameView.getKey()) {
            case 37:
                if (snake.getDx() != 1) {
                    snake.setDirectionX(-1);
                    snake.setDirectionY(0);
                }
                break;
            case 38:
                if (snake.getDy() != 1) {
                    snake.setDirectionY(-1);
                    snake.setDirectionX(0);
                }
                break;
            case 39:
                if (snake.getDx() != -1) {
                    snake.setDirectionX(1);
                    snake.setDirectionY(0);
                }
                break;
            case 40:
                if (snake.getDy() != -1) {
                    snake.setDirectionY(1);
                    snake.setDirectionX(0);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Kollar om äpplet har ätits eller inte.
     * <p>
     * Om äpplet inte existerar skapas ett nytt äpple. Om snake-huvudet har ätit äpplet
     * så annuleras äpplet och poängen ökar.
     *
     * @return om äpplet har ätits (sant eller falskt)
     */
    private boolean appleEaten() {
        boolean appleEaten = false;

        if (apple == null) {
            apple = new Apple();
        } else if (snake.getSnakeList().get(0).getXpos() == apple.getXpos() && snake.getSnakeList().get(0).getYpos() == apple.getYpos()) {
            score++;
            apple = null;
            appleEaten = true;
        }

        return appleEaten;
    }

    /**
     * Kollar om ormen har ätit sig själv.
     * <p>
     * Om så är fallet tömms snakelistan och en ny skapas med standard längd
     * och kordinater.
     */
    private void trailEaten() {
        for (int i = 4; i < snake.getSnakeList().size(); i++) {
            if (snake.getSnakeList().get(0).getXpos() == snake.getSnakeList().get(i).getXpos() && snake.getSnakeList().get(0).getYpos() == snake.getSnakeList().get(i).getYpos()) {
                int defX = snake.getSnakeList().get(0).getXpos();
                int defY = snake.getSnakeList().get(0).getYpos();

                snake.getSnakeList().clear(); // Tömmer snaken

                snake.createSnake(baseLength, defX, defY); //  Skapar ny liten snake

                score = 0;
            }
        }
    }

    /**
     * Om ormen lämnar ena sidan av skärmen återkommer den på motsatt sida.
     */
    private void wrapBorder() {
        if (snake.getSnakeList().get(0).getXpos() > gameView.columns-1) {
            snake.getSnakeList().get(0).setXpos(0);
        }
        if (snake.getSnakeList().get(0).getXpos() < 0) {
            snake.getSnakeList().get(0).setXpos(gameView.columns-1);
        }
        if (snake.getSnakeList().get(0).getYpos() > gameView.rows-1) {
            snake.getSnakeList().get(0).setYpos(0);
        }
        if (snake.getSnakeList().get(0).getYpos() < 0) {
            snake.getSnakeList().get(0).setYpos(gameView.rows-1);
        }
    }

    /**
     * Returnerar snake-objektet.
     *
     * @return referensen till modellen Snake
     */
    public Snake getSnake() {
        return this.snake;
    }

    /**
     * Returnerar apple-objektet.
     *
     * @return referensen till modellen Apple
     */
    public Apple getApple() {
        return this.apple;
    }

    /**
     * Returnerar monetära poängen.
     *
     * @return antalet äpplen ätna utan att ha dött
     */
    public int getScore() {
        return score;
    }

    /**
     * Returnerar poäng-rekordet.
     *
     * @return rekordet i antalet äpplen uppätna
     */
    public int getHighScore() {
        return highScore;
    }
}
