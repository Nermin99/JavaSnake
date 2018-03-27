import java.awt.*;

public class GameLogic {
    public Snake snake;
    public Apple apple;
    private boolean gameRunning = true;
    private int fps = 15;
    private int baseLength = 4;

    private GameView gameView;


    public GameLogic(GameView gameView) {
        this.gameView = gameView;
    }

    public void loadObjects() {
        snake = new Snake(-1, 0);

        int defX = (gameView.columns - baseLength) / 2;
        int defY = gameView.rows / 2;

        for (int i = defX; i < defX + baseLength; i++) {
            snake.extend(i, defY, Color.green);
        }

        run();
    }

    public void update() {
        updateDirection();

        trailEaten();

        snake.move(appleEaten());

        wrapBorder();
    }

    public void run() {
        long lastUpdateTime = System.nanoTime();
        int updateTime = (int)(1000000000.0/fps);

        while (gameRunning) {
            long deltaTime = System.nanoTime() - lastUpdateTime;

            if (deltaTime >= updateTime) {
                lastUpdateTime = System.nanoTime();
                update();
                gameView.render();
            }
        }
    }

    public void updateDirection() {
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

    public boolean appleEaten() {
        boolean appleEaten = false;

        if (apple == null) {
            apple = new Apple();
        } else if (snake.snakeList.get(0).xPos == apple.xPos && snake.snakeList.get(0).yPos == apple.yPos) {
            apple = null;
            appleEaten = true;
        }
        return appleEaten;
    }

    public void trailEaten() {
        for (int i = 4; i < snake.snakeList.size(); i++) {
            if (snake.snakeList.get(0).xPos == snake.snakeList.get(i).xPos && snake.snakeList.get(0).yPos == snake.snakeList.get(i).yPos) {
                for (int j = snake.snakeList.size()-1; j > baseLength - 1; j--) {
                    snake.snakeList.remove(j);
                }
            }
        }
    }

    public void wrapBorder() {
        if (snake.snakeList.get(0).xPos > gameView.columns-1) {
            snake.snakeList.get(0).xPos = 0;
        }
        if (snake.snakeList.get(0).xPos < 0) {
            snake.snakeList.get(0).xPos = gameView.columns-1;
        }
        if (snake.snakeList.get(0).yPos > gameView.rows-1) {
            snake.snakeList.get(0).yPos = 0;
        }
        if (snake.snakeList.get(0).yPos < 0) {
            snake.snakeList.get(0).yPos = gameView.rows-1;
        }
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Apple getApple() {
        return this.apple;
    }
}
