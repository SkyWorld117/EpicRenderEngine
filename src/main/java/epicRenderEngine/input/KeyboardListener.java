package epicRenderEngine.input;

import epicRenderEngine.Camera;
import epicRenderEngine.util.Vector3f;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    private final Camera cam;

    public KeyboardListener(Camera cam) {
        this.cam = cam;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch(keyCode) {
            //case event.VK_LEFT -> cam.move(new Vector3f())
        }
    }
}