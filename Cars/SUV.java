package Cars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SUV extends Vehicle {
    Image image3;
    public SUV(int newx, int newy){
        super(newx, newy);
        width = 90;
        height = 56;
        speed = 8;
        try {
            image3 = ImageIO.read(getClass().getResource("/Cars/samochodzik_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintMe(Graphics g){
        g.drawImage(image3, x, y, null);
    }
}
