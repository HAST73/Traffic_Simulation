package Road;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PieceOfImage {
    int x;
    int y;
    private Image imageIcon;

    public PieceOfImage(int newx, int newy) {
        this.x = newx;
        this.y = newy;

        try {
            imageIcon = ImageIO.read(getClass().getResource("/Road/image3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void paintImage(Graphics g){
        g.drawImage(imageIcon, x, y, null);
    }
}
