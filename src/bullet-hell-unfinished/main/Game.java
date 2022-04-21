package game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 9042685710427163391L;

    public static final int baseWidth = 1920, baseHeight = 1080;

    /* LEAVE THIS UNTIL YOU HAVE BASIC SPRITES IN THE GAME
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int screenWidth = (int)screenSize.getWidth(), screenHeight = (int)screenSize.getHeight();
    public final float scaleWidth = screenWidth / baseWidth, scaleHeight = screenHeight / baseHeight;
    */

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(baseWidth, baseHeight, "Untitled Cyberpunk-esque Dream", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();

        handler.addObject(new Player(baseWidth/2-40, baseHeight/2-40, ID.Player, handler));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocusInWindow();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, baseWidth, baseHeight);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(float var, float min, float max) {
        return (int)Math.max(min, Math.min(max, var));
    }

    public static void main(String[] args) {
        new Game();
    }

}
