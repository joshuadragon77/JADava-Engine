package testApplications;

import java.awt.*;
import java.awt.image.*;

import frameworks.ResourceLoader;
import javax.swing.*;

public class TestApp1 {
    public static void main(String[] args) throws InterruptedException{
        Image i = ResourceLoader.readImage("/resources/Window.png");

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage bi = gc.createCompatibleImage(750, 500);
        Graphics localG = bi.getGraphics();


        class H1 extends JFrame{
            H1() throws InterruptedException{
                Window window = this;//new Window(this);
                window.setIconImage(i);
                window.setSize(new Dimension(1680, 700));
                window.setBackground(new Color(29, 29, 29));
                this.setUndecorated(false);
                this.setResizable(true);
                window.setVisible(true);

                MenuBar mb = new MenuBar();
                Menu m = new Menu();
                m.setLabel("shit");
                MenuItem me = new MenuItem();
                me.setLabel("qweqwe");
                mb.add(m);
                m.add(me);
                this.setMenuBar(mb);

                this.createBufferStrategy(1);

                this.setTitle("So we kept distane. Zero resistance. The change in tone some shit I called. It shows you are not listening. Are we worth");
            }

            public void paint(Graphics g){
                g.drawImage(bi, 0, 0, null);
            }
        }
        H1 frame = new H1();
        BufferStrategy bs = frame.getBufferStrategy();

        int cycles = 0;
        while (true){
            long startTime = System.currentTimeMillis();
            Thread.sleep(16);
            localG.setColor(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
            localG.fillRect(0, 0, 1250, 700);
            localG.drawImage(i, 0, 0, null);
            bs.getDrawGraphics().drawImage(bi, 0, 0, null);

            long endTime = System.currentTimeMillis();
            cycles ++;
            if (cycles > 15){
                cycles = 0;
                System.out.println("FrameRate: " + Math.floor(1/((endTime-startTime)/1000.0)) + " FPS");
            }
        }

    }
}
