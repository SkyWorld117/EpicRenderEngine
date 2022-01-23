package epicRenderEngine;

import epicRenderEngine.util.Matrix3f;
import epicRenderEngine.util.Util;
import epicRenderEngine.util.Vector3f;

public class Camera {

    public final int screenWidth;
    public final int screenHeight;
    public Vector3f position = new Vector3f(-200.0f, 0.0f, 0.0f);
    public Vector3f direction = new Vector3f(1.0f, 0.0f, 0.0f);
    public Vector3f rotation = new Vector3f(0.0f, 0.0f, 1.0f);
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

        Vector3f cross = this.direction.cross(this.rotation).normalize();
        return position.add(cross.scale(dx)).add(rotation.scale(dy)).sub(focalPoint).normalize();
    }

    /**
     * moves camera relative to direction vector
     * @param d
     */
    public void move(Vector3f d) {
        if(d.x != 0) {
            this.position = this.position.add(this.direction.scale(d.x));
        }

        if(d.y != 0) {
            this.position = this.position.add(this.rotation.scale(d.y));
        }

        if(d.z != 0) {
            Vector3f cross = rotation.cross(direction).normalize();
            this.position = position.add(cross.scale(d.z));
        }
    }

    /**
     * Rotates camera by angles specified.
     *
     * @param yaw
     * @param pitch
     */
    public void rotate(float yaw, double pitch) {
        if(yaw != 0) {
            Matrix3f m = Util.getRodriguezMatrix(this.rotation, yaw);
            this.direction = m.mul(this.direction);
        }

        if(pitch != 0) {
            Vector3f cross = this.direction.cross(this.rotation);

            Matrix3f m = Util.getRodriguezMatrix(cross, pitch);
            this.direction = m.mul(this.direction);
            this.rotation = m.mul(this.rotation);
        }
    }
}
