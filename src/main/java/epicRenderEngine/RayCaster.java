package epicRenderEngine;

import epicRenderEngine.util.*;

import java.util.ArrayList;
import java.util.List;

public class RayCaster {

    private List<Triangle> triangles = new ArrayList<>();
    public Camera cam;
    public static final Vector3f LIGHT_DIRECTION = new Vector3f(-1.0f, -1.0f, -1.0f).normalize();

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

        double minDist = Double.MAX_VALUE;
        Triangle intersecting = null;
        double u = 0, v = 0;

        for(Triangle triangle : this.triangles) {
            double[] uv = new double[2];
            Vector3f intersectionVec = MollerTrumbore.rayIntersectsTriangle(location, ray, triangle, uv);

            if(intersectionVec != null) {
                double l = intersectionVec.sub(location).length();

                if (l < minDist) {
                    minDist = l;
                    intersecting = triangle;
                    u = uv[0];
                    v = uv[1];
                }
            }
        }

        if(intersecting != null) {
            int dl = 20 - (int) (Math.min(0, intersecting.normal.dot(LIGHT_DIRECTION)) * 20);
            int color;
            if(intersecting.getTexture() == null) {
                color = intersecting.getColor();
            } else {
                color = intersecting.getTexture().getRGB((int)(u * (intersecting.getTexture().getWidth() - 1)) , (int)(v * (intersecting.getTexture().getHeight() - 1)));
            }

            int b = (color>>16) & 0x0ff;
            int g = (color>>8) & 0x0ff;
            int r = color & 0x0ff;


            return (Math.max(0, b - dl) << 16) | (Math.max(0, g - dl) << 8) | (Math.max(0, r - dl));

        }

        return -1;
    }

    public List<Triangle> getTriangles() {
        return this.triangles;
    }
}
