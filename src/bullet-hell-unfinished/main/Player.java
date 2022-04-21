package game.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 40, 40);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.baseWidth - 55);
        y = Game.clamp(y, 0, Game.baseHeight - 75);

        collision();
    }

    private void collision() {
        for (GameObject tempObject : handler.object) {
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.health -=1;
                }
            }
            else if (tempObject.getId() == ID.Trail) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.health -=0.1;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int) x,(int) y, 40, 40);
    }

}
