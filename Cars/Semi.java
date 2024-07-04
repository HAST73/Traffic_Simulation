package Cars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Semi extends Vehicle {
    Image image2;

    public Semi(int newx, int newy){
        super(newx, newy);
        width = 110;
        height = 83;
        speed = 5;
        try {
            image2 = ImageIO.read(getClass().getResource("/Cars/Ciezarowka.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintMe(Graphics g){
        g.drawImage(image2, x, y, null);
    }
}
