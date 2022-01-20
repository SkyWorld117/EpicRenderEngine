package epicRenderEngine;

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

        //create FrameWrapper
        FrameWrapper fw = new FrameWrapper("Frame", WIN_WIDTH, WIN_HEIGHT);
        fw.setVisible(true);

        //initialize RayCaster
        RayCaster rayCaster = new RayCaster(WIN_WIDTH, WIN_HEIGHT);

        try {
            Triangle[] triangles = ObjectReader.load("data.txt");
            rayCaster.addTriangles(triangles);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean running = true;
        double fps = 3.0;
        long t0 = System.currentTimeMillis();
        long ticks = 0;

        //render loop
        while(running) {
            long t1 = System.currentTimeMillis();
            long dt = t1 - t0;
            ticks += dt;

            if(ticks > 1000.0 / fps) {
                render(fw, rayCaster);
                ticks = 0;
                break;

            }

            t0 = t1;
        }
    }

    /**
     * Renders the scene
     * @param fw
     * @param rayCaster
     */
    private static void render(FrameWrapper fw, RayCaster rayCaster) {
        rayCaster.castRays(fw);

        //update canvas
        fw.repaint();
    }
}
