package epicRenderEngine;

import epicRenderEngine.util.Vector3f;

public class Camera {

    public final double screenWidth;
    public final double screenHeight;
    private Vector3f position = new Vector3f(-5.0f, 0.0f, 0.0f);
    private Vector3f direction = new Vector3f(1.0f, 0.0f, 0.0f);
    private double FOV = 90.0;

    public Camera(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Vector3f getFocalPoint() {
        return position.add(new Vector3f((float)( -screenWidth/ 2.0 / Math.tan(this.FOV)), 0.0f, 0.0f));
    }

    public Vector3f getVecThroughPoint(Vector3f focalPoint, double x, double y) {
        float dx = (float)(x - screenWidth / 2);
        float dy = (float)(y - screenHeight / 2);
        return position.addX(dx).addY(dy).sub(focalPoint).normalize();
    }

    public void move(Vector3f d) {
        this.position = this.position.add(d);
    }

    public void rotate(float phi, double theta) {
        //this.position = this.position.add();
    }
}
