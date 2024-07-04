package Road;

import Cars.Vehicle;
import Pedestrians.OlderGentelman;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Road extends JPanel {
    final int height = 150;
    private final List<Vehicle> upperVehicles = new ArrayList<>();
    private final List<Vehicle> lowerVehicles = new ArrayList<>();
    private final List<OlderGentelman> olderGentelmens = new ArrayList<>();
    private PieceOfImage pieceOfImage = new PieceOfImage(208,98);
    public final int ROAD_WIDTH = 1000;
    public final int ROAD_HEIGHT = 800; // ?
    public final int LANE_HEIGHT = 100;

    public Road(){
        super();
    }

    public void addCarLower(Vehicle v){
        lowerVehicles.add(v);
    }

    public void addCarUpper(Vehicle z){
        upperVehicles.add(z);
    }

    public void addOldPedestrian(OlderGentelman h){
        olderGentelmens.add(h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // above the road in green
        g.setColor(new Color(0,102,0));
        g.fillRect(0, 0, this.getWidth(), height);

        // below the road in green
        g.fillRect(0, height + 400, this.getWidth(), this.getHeight() - (height + 200));

        g.setColor(Color.BLACK);
        g.fillRect(0, height, this.getWidth(), 400);
        g.setColor(Color.WHITE);

        for (int a = height-25; a >= 0; a--) { // first line left up
            g.fillRect(300, a, 5, 30);
        }

        g.setColor(Color.BLACK);
        for(int j = 0;j<getHeight();j++){ // upper black road going up I mean
            for(int a = 305;a<500;a++){
                g.fillRect(a, j, 5, 5);
            }
        }

        g.setColor(Color.WHITE);

        for (int a = height-25; a >= -10; a-=45) { // line going up white
            g.fillRect(400, a, 5, 30);
        }

        for (int a = height-25; a >= 0; a--) { // first line right up
            g.fillRect(500, a, 5, 30);
        }

        for (int a = (height*2)+250; a < getHeight(); a++) { // first line right down
            g.fillRect(500, a, 5, 30);
        }

        for (int a = (height*2)+250; a < getHeight(); a++) { // first line left down
            g.fillRect(300, a, 5, 30);
        }

        for (int a = (height*2)+250; a < getHeight(); a+=45) { // middle white dashed line going down
            g.fillRect(400, a, 5, 30);
        }

        for (int a = 0; a < getWidth(); a = a + 45) { // middle first white dashed line going on right
            g.fillRect(a, height + 100, 30, 5);
        }

        for (int a = 0; a < getWidth(); a = a + 45) { // middle second white dashed line going on right
            g.fillRect(a, height + 300, 30, 5);
        }

        for (int a = 0; a < 275; a++) { // first line left white up
            g.fillRect(a, height, 30, 5);
        }

        for (int a = 500; a < getWidth(); a++) { // first line right white up
            g.fillRect(a, height, 30, 5);
        }

        for (int a = 0; a < getWidth(); a++) { // third line
            g.fillRect(a, height + 200, 30, 5);
        }

        for (int a = 0; a < 275; a++) { // first line left white down
            g.fillRect(a, height+400, 30, 5);
        }

        for (int a = 500; a < getWidth(); a++) { // second line left white down
            g.fillRect(a, height+400, 30, 5);
        }

        for(int j=527; j>=150; j=j-40){
            g.fillRect(610, j, 70, 10); // pedestrians crossing
        }

        pieceOfImage.paintImage(g);

        for(int a = 0; a<lowerVehicles.size(); a++){
            lowerVehicles.get(a).paintMe(g);
        }

        for(int a = 0; a<upperVehicles.size(); a++){
            upperVehicles.get(a).paintMe(g);
        }

        for(int a = 0; a< olderGentelmens.size(); a++){
            olderGentelmens.get(a).paintMe(g); // to pedestrians
        }
    }

    public void stepDown(){
        for(int a = 0; a < upperVehicles.size(); a++){
            Vehicle z = upperVehicles.get(a);
            boolean hasCollisionWithPedestrian = false;

            for (int i = 0; i < olderGentelmens.size(); i++) {
                OlderGentelman h = olderGentelmens.get(i);
                if (isPedestrianInCrosswalk(z.getX()-50, z.getY()-70, h)) {
                    hasCollisionWithPedestrian = true;
                    break;
                }
            }

            if(!hasCollisionWithPedestrian && !collisionUpper(z.getX() - z.getSpeed(), z.getY(), z)){
                z.setX(z.getX() - z.getSpeed());
                if(z.getX() + z.getWidth() < 5){
                    if(!collisionUpper(ROAD_WIDTH, z.getY(), z)){
                        z.setX(ROAD_WIDTH + z.getWidth());
                    }
                }
            }
            else if(hasCollisionWithPedestrian){
                z.setX(z.getX());
            }
            else {
                if ((z.getY() >= 160 && z.getY() <=190) && !collisionUpper(z.getX(), z.getY() + LANE_HEIGHT, z)) {
                    z.setY(z.getY() + LANE_HEIGHT);
                }
                else if ((z.getY() > 200) && !collisionUpper(z.getX(), z.getY() - LANE_HEIGHT, z)) {
                    z.setY(z.getY() - LANE_HEIGHT);
                }
            }
        }
    }

    private boolean isPedestrianInCrosswalk(int x, int y, OlderGentelman h) {
        return x >= 590 && x <= 690 && y >= h.getY() - 100 && y <= h.getY() + h.getHeight() + 100;
    }

    public void stepUp(){
        for(int a = 0; a < lowerVehicles.size(); a++){
            Vehicle v = lowerVehicles.get(a);
            boolean hasCollisionWithPedestrian = false;

            for (int i = 0; i < olderGentelmens.size(); i++) {
                OlderGentelman h = olderGentelmens.get(i);
                if (isPedestrianInCrosswalk(v.getX() + v.getWidth() + 50, v.getY() - 70, h)) {
                    hasCollisionWithPedestrian = true;
                    break;
                }
            }

            if(!hasCollisionWithPedestrian && !collisionLower(v.getX() + v.getSpeed(), v.getY(), v)){
                v.setX(v.getX() + v.getSpeed());
                if(v.getX() > ROAD_WIDTH){
                    if(!collisionLower(0, v.getY(), v)){
                        v.setX(-v.getWidth());
                    }
                }
            }
            else if(hasCollisionWithPedestrian){
                v.setX(v.getX());

            }
            else {
                if ((v.getY() >= 460 && v.getY() <=490) && !collisionLower(v.getX(), v.getY() - LANE_HEIGHT, v)) {
                    v.setY(v.getY() - LANE_HEIGHT);
                }
                else if ((v.getY() < 460) && !collisionLower(v.getX(), v.getY() + LANE_HEIGHT, v)) {
                    v.setY(v.getY() + LANE_HEIGHT);
                }
            }
        }
    }

    public void stepOldPedestrianDown(){ //ruch pieszego
        for(int a = 0; a < olderGentelmens.size(); a++) {
            OlderGentelman h = olderGentelmens.get(a);
            if(!collisionOldPedestriansLowerWithOther(h.getX(), h.getY() + h.getSpeed(), h)){
                h.setY(h.getY() + h.getSpeed());
                if(h.getY() > ROAD_HEIGHT){
                    if(!collisionOldPedestriansLowerWithOther(h.getX(), 0, h)){
                        h.setY(-h.getHeight());
                    }
                }
            }
        }
    }

    public void stepOldPedestrianUp(){
        for(int a = 0; a < olderGentelmens.size(); a++) {
            OlderGentelman h = olderGentelmens.get(a);
            if(!collisionOldPedestriansUpperWithOther(h.getX(), h.getY() - h.getSpeed(), h)){
                h.setY(h.getY() - h.getSpeed());
                if(h.getY() < -h.getHeight()){
                    if(!collisionOldPedestriansUpperWithOther(h.getX(), ROAD_HEIGHT, h)){
                        h.setY(ROAD_HEIGHT + h.getHeight());
                    }
                }
            }
        }
    }

    public boolean collisionLower(int x, int y, Vehicle v){
        for(int a = 0; a < lowerVehicles.size(); a++){
            Vehicle u = lowerVehicles.get(a);
            if(u != v && u.getBounds().intersects(x, y, v.getWidth(), v.getHeight())){
                return true;
            }
        }
        return false;
    }

    public boolean collisionUpper(int x, int y, Vehicle z){
        for(int a = 0; a < upperVehicles.size(); a++){
            Vehicle u = upperVehicles.get(a);
            if(u != z && u.getBounds().intersects(x, y, z.getWidth(), z.getHeight())){
                return true;
            }
        }
        return false;
    }

    public boolean collisionOldPedestriansLowerWithOther(int x, int y, OlderGentelman h){
        for(int a = 0; a < olderGentelmens.size(); a++){
            OlderGentelman u = olderGentelmens.get(a);
            if(u != h && u.getBounds().intersects(x, y, h.getWidth(), h.getHeight())){
                return true;
            }
        }
        return false;
    }

    public boolean collisionOldPedestriansUpperWithOther(int x, int y, OlderGentelman h){
        for(int a = 0; a < olderGentelmens.size(); a++){
            OlderGentelman u = olderGentelmens.get(a);
            if(u != h && u.getBounds().intersects(x, y, h.getWidth(), h.getHeight())){
                return true;
            }
        }
        return false;
    }

    public PieceOfImage getPieceOfImage() {
        return pieceOfImage;
    }
}
