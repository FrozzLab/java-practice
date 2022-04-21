package game.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 20, 20);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.baseWidth-20) velX *= -1;
        if (y <= 0 || y >= Game.baseHeight-40) velY *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 20, 20, 0.03f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x,(int) y, 20, 20);
    }

}
