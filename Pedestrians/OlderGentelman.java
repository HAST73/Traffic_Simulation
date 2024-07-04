package Pedestrians;

import Cars.Vehicle;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OlderGentelman extends Vehicle {
    private Image image4;
    public OlderGentelman(int newx, int newy){
        super(newx, newy);
        width = 22;
        height = 40;
        speed = 3;
        try {
            image4 = ImageIO.read(getClass().getResource("/Pedestrians/oldman_v3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintMe(Graphics g){
        g.drawImage(image4, x, y, null);
    }
}
