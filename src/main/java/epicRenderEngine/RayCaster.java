package epicRenderEngine;

import epicRenderEngine.util.*;

import java.util.ArrayList;
import java.util.List;

public class RayCaster {

    private List<Triangle> triangles = new ArrayList<>();
    public Camera cam;

    public RayCaster(int screenWidth, int screenHeight) {
        this.cam = new Camera(screenWidth, screenHeight);
    }

    public void addTriangles(Triangle[] triangles) {
        this.triangles.addAll(List.of(triangles));
    }

    /**
     * Casts a ray at the pixel specified and returns color
     * @param p
     * @return
     */
    public void castRays(RaycasterPanel p) {
        Vector3f focalPoint = cam.getFocalPoint();

        for(int i = 0; i < cam.screenWidth; i++) {
            for(int j = 0; j < cam.screenHeight; j++) {
                int color = this.castPixel(cam.position, cam.getVecThroughPoint(focalPoint, i, j));
                p.setPixel(i, j, color);
            }
        }
    }

    /**
     * Casts a ray through pixel at i, j and returns color of intersecting Triangle. If there is no intersection, returns -1.
     *
     * @return
     */
    private int castPixel(Vector3f location, Vector3f ray) {
        Triangle intersecting = getIntersectingTriangle(location, ray);

        if(intersecting != null) {
            return intersecting.getColor();
        }

        return -1;
    }


    private Triangle getIntersectingTriangle(Vector3f location, Vector3f direction) {
        double minDist = Double.MAX_VALUE;
        Triangle intersecting = null;
        for(Triangle triangle : this.triangles) {
            Vector3f intersectionVec = MollerTrumbore.rayIntersectsTriangle(location, direction, triangle);

            if(intersectionVec == null) continue;

            double l = intersectionVec.sub(location).length();

            if(l < minDist) {
                minDist = l;
                intersecting = triangle;
            }
        }

        return intersecting;
    }
}
