package Cars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SportsCar extends Vehicle {

    Image image1;

    public SportsCar(int newx, int newy){
        super(newx, newy);
        width = 70;
        height = 35;
        speed = 12;
        try {
            image1 = ImageIO.read(getClass().getResource("/Cars/Audi_v10_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintMe(Graphics g){
        g.drawImage(image1, x, y, null);
    }
}
