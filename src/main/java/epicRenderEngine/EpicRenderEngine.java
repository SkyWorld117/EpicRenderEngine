package epicRenderEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EpicRenderEngine {
    
    public static void main(String[] args) {
        BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i < 500; i++) {
            for(int j = 0; j < 500; j++) {
                bi.getRaster().setPixel(i, j, new int[]{i , j , i + j});
            }
        }

        JFrame frame = new JFrame("FrameDemo") {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(bi, 0, 0, 500, 500, null);
            }
        };

        frame.pack();
        frame.setSize(500, 500);


        frame.setVisible(true);
    }
}
