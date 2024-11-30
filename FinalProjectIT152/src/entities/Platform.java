package entities;

import java.awt.Color;
import java.awt.Graphics;

public class Platform {
    private int x, y, width, height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height ;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y + 43, width, height);
    }

    // Getters for collision detection
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width ;
    }

    public int getHeight() {
        return height ;
    }
}
