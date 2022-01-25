package epicRenderEngine.input;

import epicRenderEngine.Camera;
import epicRenderEngine.util.Vector3f;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    private static final float MOVE_SPEED = 15.0f;
    private static final float ROT_SPEED = (float)Math.toRadians(1.5);

    private final Camera cam;
    private Vector3f dMove = Vector3f.ZERO;
    private float dYaw = 0;
    private float dPitch = 0;
    private boolean isDirty = false;

    public KeyboardListener(Camera cam) {
        this.cam = cam;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();

        //translation
        if(keyCode == KeyEvent.VK_DOWN) dMove = dMove.addX(-MOVE_SPEED);
        if(keyCode == KeyEvent.VK_UP) dMove = dMove.addX(MOVE_SPEED);
        if(keyCode == KeyEvent.VK_LEFT) dMove = dMove.addY(MOVE_SPEED);
        if(keyCode == KeyEvent.VK_RIGHT) dMove = dMove.addY(-MOVE_SPEED);
        if(keyCode == KeyEvent.VK_SPACE) dMove = dMove.addZ(MOVE_SPEED);
        if(keyCode == KeyEvent.VK_SHIFT) dMove = dMove.addZ(-MOVE_SPEED);

        //rotation
        if(keyCode == KeyEvent.VK_W) dPitch += ROT_SPEED;
        if(keyCode == KeyEvent.VK_S) dPitch -= ROT_SPEED;
        if(keyCode == KeyEvent.VK_A) dYaw += ROT_SPEED;
        if(keyCode == KeyEvent.VK_D) dYaw -= ROT_SPEED;

        this.isDirty = true;
    }

    public void updateParameters() {
        if(this.isDirty) {
            this.isDirty = false;

            cam.move(dMove);
            cam.rotate(dYaw, dPitch);

            dMove = Vector3f.ZERO;
            dYaw = 0;
            dPitch = 0;
        }
    }
}