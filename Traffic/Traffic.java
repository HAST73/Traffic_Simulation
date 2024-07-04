package Traffic;

import Cars.SUV;
import Cars.Semi;
import Cars.SportsCar;
import Pedestrians.OlderGentelman;
import Road.Road;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Traffic extends JFrame implements Runnable, ActionListener {
    private Road road;
    int WIDTH = 1000;
    int HEIGHT = 800;
    JButton start;
    JButton stop;
    JButton addCar1;
    JButton addCar2;
    JButton addCar3;
    JButton addCar4;
    JButton addCar5;
    JButton addCar6;
    JButton exit;
    JButton addPedestrian;
    boolean running = false;

    public Traffic(){
        JOptionPane.showMessageDialog(this, "Welcome to the Traffic Simulation", "Information", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, "Are you ready?", "Question", JOptionPane.QUESTION_MESSAGE);

        setTitle("Traffic Simulation");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        road = new Road();

        road.setLayout(null);
        add(road);

        start = new JButton("Start");
        start.setBounds(90,570,80,30);
        start.addActionListener(this);
        road.add(start);

        stop = new JButton("Stop");
        stop.setBounds(90,610,80,30);
        stop.addActionListener(this);
        road.add(stop);

        addCar1 = new JButton("Add a slow car");
        addCar1.setBounds(55,650,145,25);
        addCar1.addActionListener(this);
        road.add(addCar1);

        addCar2 = new JButton("Add a average car");
        addCar2.setBounds(55,690,145,25);
        addCar2.addActionListener(this);
        road.add(addCar2);

        addCar3 = new JButton("Add a fast car");
        addCar3.setBounds(55,730,145,25);
        addCar3.addActionListener(this);
        road.add(addCar3);

        addCar4 = new JButton("Add a slow car");
        addCar4.setBounds(825,30,145,25);
        addCar4.addActionListener(this);
        road.add(addCar4);

        addCar5 = new JButton("Add a average car");
        addCar5.setBounds(825,65,145,25);
        addCar5.addActionListener(this);
        road.add(addCar5);

        addCar6 = new JButton("Add a fast car");
        addCar6.setBounds(825,100,145,25);
        addCar6.addActionListener(this);
        road.add(addCar6);

        exit = new JButton("Exit");
        exit.setBounds(890,720,80,30);
        exit.addActionListener(this);
        road.add(exit);

        addPedestrian = new JButton("Add a slow man ");
        addPedestrian.setBounds(55,30,145,25);
        addPedestrian.addActionListener(this);
        road.add(addPedestrian);

        Semi testSemi = new Semi(10, 460);
        road.addCarLower(testSemi);

        Semi testSemi1 = new Semi(800, 160);
        road.addCarUpper(testSemi1);

        SUV testSUV = new SUV(-100, 470);
        road.addCarLower(testSUV);

        SUV testSUV1 = new SUV(940, 170);
        road.addCarUpper(testSUV1);

        SportsCar testSportsCar = new SportsCar(-170, 490);
        road.addCarLower(testSportsCar);

        SportsCar testSportsCar1 = new SportsCar(1030, 190);
        road.addCarUpper(testSportsCar1);

        OlderGentelman olderGentelman = new OlderGentelman(650, 0);
        road.addOldPedestrian(olderGentelman);

        repaint();

        setVisible(true);
    }

    @Override
    public void run() {
        while (running == true){
            road.stepUp();
            road.stepDown();
            road.stepOldPedestrianDown();
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == start){
            if(running == false){
                running = true;
                Thread t = new Thread(this);
                t.start();
            }
        }
        else if(source == stop){
            running = false;
        }
        else if(source == addCar1){
            Semi semi = new Semi(0, 460);
            road.addCarLower(semi);
            for(int x = 0; x < road.ROAD_WIDTH; x = x + 20){
                int y = 460;
                semi.setX(x);
                semi.setY(y);
                if(road.collisionLower(x, y, semi) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == addCar2){
            SUV suv = new SUV(0, 470);
            road.addCarLower(suv);
            for(int x = 0; x < road.ROAD_WIDTH; x = x + 20){
                int y = 470;
                suv.setX(x);
                suv.setY(y);
                if(road.collisionLower(x, y, suv) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == addCar3){
            SportsCar sportsCar = new SportsCar(0, 490);
            road.addCarLower(sportsCar);
            for(int x = 0; x < road.ROAD_WIDTH; x = x + 20){
                int y = 490;
                sportsCar.setX(x);
                sportsCar.setY(y);
                if(road.collisionLower(x, y, sportsCar) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == addCar4){
            Semi semi1 = new Semi(600, 160);
            road.addCarUpper(semi1);
            for(int x = 600; x < road.ROAD_WIDTH; x = x - 20){
                int y = 160;
                semi1.setX(x);
                semi1.setY(y);
                if(road.collisionUpper(x, y, semi1) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == addCar5){
            SUV suv1 = new SUV(600, 170);
            road.addCarUpper(suv1);
            for(int x = 600; x < road.ROAD_WIDTH; x = x - 20){
                int y = 170;
                suv1.setX(x);
                suv1.setY(y);
                if(road.collisionUpper(x, y, suv1) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == addCar6){
            SportsCar sportsCar1 = new SportsCar(600, 190);
            road.addCarUpper(sportsCar1);
            for(int x = 600; x < road.ROAD_WIDTH; x = x - 20){
                int y = 190;
                sportsCar1.setX(x);
                sportsCar1.setY(y);
                if(road.collisionUpper(x, y, sportsCar1) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == addPedestrian){
            OlderGentelman olderGentelman = new OlderGentelman(650, 10);
            road.addOldPedestrian(olderGentelman);
            for(int y = 10; y < road.ROAD_HEIGHT; y = y + 30){
                int x = 650;
                olderGentelman.setX(x);
                olderGentelman.setY(y);
                if(road.collisionOldPedestriansLowerWithOther(x, y, olderGentelman) == false){
                    repaint();
                    return;
                }
            }
        }
        else if(source == exit){
            dispose();
        }
    }
}
