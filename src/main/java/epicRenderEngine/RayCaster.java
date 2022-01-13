package epicRenderEngine;

import epicRenderEngine.util.Triangle;

import java.util.ArrayList;
import java.util.List;

public class RayCaster {

    List<Triangle> triangles = new ArrayList<>();
    Camera cam;

    public RayCaster(double screenWidth, double screenHeight) {
        this.cam = new Camera(screenWidth, screenHeight);
    }

    /**
     * Casts a ray at the pixel specified and returns color
     *
     * @param x
     * @param y
     * @return
     */
    public int castPixel(int x, int y) {
        return 0;
    }


    private Triangle getIntersectingTriangle() {
        return null;
    }
}
