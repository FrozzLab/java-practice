package game.main;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (GameObject tempObject : handler.object) {
            if (tempObject.getId() == ID.Player) player = tempObject;
        }


    }

    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 20, 20);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 10;
        float diffY = y - player.getY() - 10;
        float distance = (float) Math.sqrt( (x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()) );

        velX = ((-1/distance) * diffX * 5);
        velY = ((-1/distance) * diffY * 5);

        if (x <= 0 || x >= Game.baseWidth-20) velX *= -1;
        if (y <= 0 || y >= Game.baseHeight-40) velY *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 20, 20, 0.03f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x,(int) y, 20, 20);
    }

}
