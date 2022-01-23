package epicRenderEngine.input;

import epicRenderEngine.Camera;
import epicRenderEngine.util.Vector3f;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    private final Camera cam;
    private final float MOVE_SPEED = 3.0f;
    private final float ROT_SPEED = (float)Math.toRadians(1.0);

    public KeyboardListener(Camera cam) {
        this.cam = cam;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();

        //translation
        if(keyCode == KeyEvent.VK_DOWN) cam.move(new Vector3f(MOVE_SPEED, 0, 0));
        if(keyCode == KeyEvent.VK_UP) cam.move(new Vector3f(-MOVE_SPEED, 0, 0));
        if(keyCode == KeyEvent.VK_LEFT) cam.move(new Vector3f(0, 0, MOVE_SPEED));
        if(keyCode == KeyEvent.VK_RIGHT) cam.move(new Vector3f(0, 0, -MOVE_SPEED));
        if(keyCode == KeyEvent.VK_SPACE) cam.move(new Vector3f(0, -MOVE_SPEED, 0));
        if(keyCode == KeyEvent.VK_SHIFT) cam.move(new Vector3f(0, MOVE_SPEED, 0));

        //rotation
        if(keyCode == KeyEvent.VK_W) cam.rotate(ROT_SPEED, 0);
        if(keyCode == KeyEvent.VK_S) cam.rotate(-ROT_SPEED, 0);
        if(keyCode == KeyEvent.VK_A) cam.rotate(0, ROT_SPEED);
        if(keyCode == KeyEvent.VK_D) cam.rotate(0, -ROT_SPEED);
    }
}