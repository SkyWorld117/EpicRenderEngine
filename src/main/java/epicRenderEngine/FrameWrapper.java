package epicRenderEngine;

import epicRenderEngine.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameWrapper extends JFrame {

    public BufferedImage pixelBuffer;

    public FrameWrapper(String name, int width, int height) {
        super(name);
        this.pack();
        this.setSize(width, height);

        this.pixelBuffer = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.pixelBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void setPixel(int x, int y, int r, int g, int b) {
        this.pixelBuffer.setRGB(x, y, Util.packRGB(r, b ,g));
    }
}
