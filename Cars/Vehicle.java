package Cars;

import java.awt.*;

public class Vehicle {
    public int x;
    public int y;
    public int width = 0;
    public int height = 0;
    public int speed = 0;

    public  Vehicle(int newx, int newy){
        x = newx;
        y = newy;
    }

    public int getX() {
        return x;
    }

    public void setX(int newx) {
        this.x = newx;
    }

    public int getY() {
        return y;
    }

    public void setY(int newy) {
        this.y = newy;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHeight() {
        return height;
    }

    public void paintMe(Graphics g){
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
