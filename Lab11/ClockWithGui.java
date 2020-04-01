package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;

public class ClockWithGui extends JPanel {
    LocalTime time = LocalTime.now();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public ClockWithGui() {
        super();
        ClockThread clock = new ClockThread();
        clock.start();
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        for(int i=1;i<13;i++){
            String number = Integer.toString(i);
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(number,
                    (int)trg.getX()-g2d.getFontMetrics().stringWidth(number)/2,
                    (int)trg.getY()+g2d.getFontMetrics().getHeight()/2);
        }

        for(int i=1;i<61;i++){
            if (i%5 != 0) {
                AffineTransform saveAT =g2d.getTransform();
                g2d.rotate(2*Math.PI/60*i);
                g2d.drawLine(0, -130, 0, -120);
                g2d.setTransform(saveAT);
            }
        }

        AffineTransform saveAT = g2d.getTransform();
        g2d.setStroke(new BasicStroke(3, CAP_ROUND, JOIN_ROUND));
        g2d.rotate(time.getHour()%12*2*Math.PI/12);
        g2d.drawLine(0,0,0,-70);
        g2d.setTransform(saveAT);

        g2d.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_ROUND));
        g2d.rotate(time.getMinute()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);

        g2d.setColor(Color.RED);
        g2d.rotate(time.getSecond()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);
    }

    public class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}
