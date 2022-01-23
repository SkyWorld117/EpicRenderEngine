package epicRenderEngine;

import epicRenderEngine.input.KeyboardListener;
import epicRenderEngine.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
/**
 * FrameWrapper paints a picture and shows it as JFrame
 */
public class RaycasterPanel extends JPanel {

    private Camera cam;
    public BufferedImage pixelBuffer;

    public RaycasterPanel(int width, int height, Camera cam) {
        this.cam = cam;
        this.pixelBuffer = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(this.pixelBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
        g.setColor(Color.BLACK);

        String s = String.format("pos=%.1f, %.1f, %.1f phi=%.2f theta=%.2f",
                cam.position.x, cam.position.y, cam.position.z,
                Math.atan(cam.direction.y / cam.direction.x),
                Math.atan(Math.sqrt(cam.direction.x * cam.direction.x + cam.direction.y * cam.direction.y) / cam.direction.z));

        g.drawString(s, getWidth() - 250, 40);
    }

    public void setPixel(int x, int y, int color) {
        this.pixelBuffer.setRGB(x, y, color);
    }
}
