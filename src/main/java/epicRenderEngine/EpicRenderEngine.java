package epicRenderEngine;

import epicRenderEngine.input.KeyboardListener;
import epicRenderEngine.input.ObjectReader;
import epicRenderEngine.util.Triangle;
import epicRenderEngine.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

class EpicRenderEngine {

    public static final int WIN_WIDTH = 600;
    public static final int WIN_HEIGHT = 400;

    public static void main(String[] args) {
        //initialize RayCaster
        RayCaster rayCaster = new RayCaster(WIN_WIDTH, WIN_HEIGHT);

        //create FrameWrapper
        JFrame f = new JFrame("Frame");

        f.addKeyListener(new KeyboardListener(rayCaster.cam));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        RaycasterPanel p = new RaycasterPanel(WIN_WIDTH, WIN_HEIGHT, rayCaster.cam);
        f.add(p);
        f.pack();
        f.setSize(WIN_WIDTH, WIN_HEIGHT);

        f.setVisible(true);

        try {
            Triangle[] triangles = ObjectReader.load("data.txt");
            rayCaster.addTriangles(triangles);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean running = true;
        double fps = 30.0;
        long t0 = System.currentTimeMillis();
        long ticks = 0;

        //render loop
        while(running) {
            long t1 = System.currentTimeMillis();
            long dt = t1 - t0;
            ticks += dt;

            if(ticks > 1000.0 / fps) {
                render(f, p, rayCaster);
                ticks = 0;
            }

            t0 = t1;
        }
    }

    /**
     * Renders the scene
     * @param p
     * @param rayCaster
     */
    private static void render(JFrame f, RaycasterPanel p, RayCaster rayCaster) {
        rayCaster.castRays(p);

        //update canvas
        f.repaint();
    }
}
