package epicRenderEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class EpicRenderEngine {

    public static final int WIN_WIDTH = 600;
    public static final int WIN_HEIGHT = 400;

    public static void main(String[] args) {
        FrameWrapper fw = new FrameWrapper("Frame", 255, 255);
        for(int i = 0; i < 255; i++) {
            for(int j = 0; j < 255; j++) {
                fw.setPixel(i, j, i , j , i * j);
            }
        }

        fw.setVisible(true);
    }
}
