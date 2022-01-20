package epicRenderEngine;

import epicRenderEngine.util.MollerTrumbore;
import epicRenderEngine.util.Triangle;
import epicRenderEngine.util.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class RayCaster {

    private List<Triangle> triangles = new ArrayList<>();
    public Camera cam;

    public RayCaster(double screenWidth, double screenHeight) {
        this.cam = new Camera(screenWidth, screenHeight);
    }

    public void addTriangles(Triangle[] triangles) {
        this.triangles.addAll(List.of(triangles));
    }

    /**
     * Casts a ray at the pixel specified and returns color
     * @param fw
     * @return
     */
    public void castRays(FrameWrapper fw) {
        Vector3f focalPoint = cam.getFocalPoint();
        for(int i = 0; i < cam.screenWidth; i++) {
            for(int j = 0; j < cam.screenHeight; j++) {
                int color = this.castPixel(focalPoint, i, j);
                fw.setPixel(i, j, color);
            }
        }
    }

    /**
     * Casts a ray through pixel at i, j and returns color of intersecting Triangle. If there is no intersection, returns -1.
     *
     * @param focalPoint
     * @param i
     * @param j
     * @return
     */
    private int castPixel(Vector3f focalPoint, int i, int j) {
        Vector3f ray = this.cam.getVecThroughPoint(focalPoint, i, j);
        Triangle intersecting = getIntersectingTriangle(focalPoint, ray);
        if(i % 10 == 0 && j % 10 == 0) System.out.println(i + " " + j + " " + ray + " " + intersecting);

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
