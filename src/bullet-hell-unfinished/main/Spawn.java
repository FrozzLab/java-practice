package game.main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 150) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 1)
                handler.addObject(new BasicEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.BasicEnemy, handler));

            else if (hud.getLevel() == 2)
                handler.addObject(new BasicEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.BasicEnemy, handler));

            else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.FastEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.FastEnemy, handler));
            }

            else if (hud.getLevel() == 4) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.BasicEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.FastEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.FastEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.FastEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.FastEnemy, handler));
            }

            else if (hud.getLevel() == 5)
                handler.addObject(new SmartEnemy(r.nextInt(Game.baseWidth - 50), r.nextInt(Game.baseHeight - 50), ID.SmartEnemy, handler));

            else if (hud.getLevel() == 8) {
                handler.clearEnemies();
                handler.addObject(new EnemyBoss((Game.baseWidth / 2) - 80, -140, ID.EnemyBoss, handler));
            }
        }
    }

}
