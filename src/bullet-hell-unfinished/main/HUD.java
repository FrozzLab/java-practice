package game.main;

import java.awt.*;

public class HUD {

    public static float maxHealth = 100;
    public static float health = 100;
    private float greenValue = 255;

    private int score = 0;
    private int level = 1;

    public void tick() {
        health = Game.clamp(health, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = (255/maxHealth*health);

        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(35, 35, 410, 70);
        g.setColor(Color.gray);
        g.fillRect(40, 40, 400, 60);
        g.setColor(new Color(100, (int)greenValue, 0));
        g.fillRect(40, 40, (int)health * 4, 60);

        g.setColor(Color.white);
        g.drawString("Score " + score, 35, 130);
        g.drawString("Level " + level, 35, 150);
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
}
