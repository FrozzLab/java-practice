package game.main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private Handler handler;
    Random r = new Random();
    private int timer = 120;

    public EnemyBoss(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 120, 120);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer == 0) velY = 0;
        timer--;

        if (timer == -100)
            velX = 15;

        if (timer <= -100 && (timer % 7) == 0) handler.addObject(new EnemyBossBullet((int)x + 60, (int)y + 60, ID.BasicEnemy, handler));

        int spawn = r.nextInt(100);
        if (timer <= -100 && spawn == 0) handler.addObject(new FastEnemy((int)x + 60, (int)y + 60, ID.FastEnemy, handler));

        if (x <= 0 || x >= Game.baseWidth-20) velX *= -1;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x,(int) y, 120, 120);
    }

}
