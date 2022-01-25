package epicRenderEngine;

import epicRenderEngine.util.Matrix3f;
import epicRenderEngine.util.Util;
import epicRenderEngine.util.Vector3f;

public class Camera {

    public final int screenWidth;
    public final int screenHeight;
    public Vector3f position = new Vector3f(-1500.0f, 0.0f, 300.0f);
    public Vector3f direction = new Vector3f(1.0f, 0.0f, 0.0f);

    public double FOV = Math.toRadians(80.0);

    public Camera(int screenWidth, int screenHeight) {
        //Constructor
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Vector3f getFocalPoint() {
        //method calculates the origin vector for the rays
        float distance = (float)( -screenWidth/ 2.0 / Math.tan(this.FOV/2.0));
        return position.add(direction.scale(distance));
    }

    public Vector3f getVecThroughPoint(Vector3f focalPoint, double x, double y) {
        //method calculates the direction vector
        float dx = (float)(x - screenWidth / 2);
        float dy = (float)(y - screenHeight / 2);

        Vector3f yawVec = this.direction.cross(Vector3f.EZ).normalize();
        Vector3f pitchVec = this.direction.cross(yawVec).normalize();
        return position.add(yawVec.scale(dx)).add(pitchVec.scale(dy)).sub(focalPoint).normalize();
    }

    /**
     * moves camera relative to direction vector
     * @param d
     */
    public void move(Vector3f d) {
        this.position = this.position.add(d);
    }

    /**
     * Rotates camera by angles specified.
     *
     * @param yaw
     * @param pitch
     */
    public void rotate(float yaw, double pitch) {
        if(yaw != 0) {
            Matrix3f m = Util.getRotationZ(yaw);
            this.direction = m.mul(this.direction);
        }

        if(pitch != 0) {
            Vector3f yawVec = this.direction.cross(Vector3f.EZ);
            Matrix3f m = Util.getRodriguezMatrix(yawVec, pitch);
            this.direction = m.mul(this.direction);
        }
    }
}
