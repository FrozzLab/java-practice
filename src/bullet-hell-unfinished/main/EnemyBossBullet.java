package game.main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

    private Handler handler;
    Random r = new Random();

    public EnemyBossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        int dir = r.nextInt(2);
        if (dir == 0) {
            velX = (r.nextInt(10 - -10) - 10);
            velY = 8;
        }
        if (dir == 1) {
            velX = (r.nextInt(10 - -10) - 10);
            velY = -8;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 20, 20);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x >= Game.baseWidth || x <= 0) handler.removeObject(this);
        if (y >= Game.baseHeight || y <= 0) handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, Color.white, 20, 20, 0.03f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x,(int) y, 20, 20);
    }

}
