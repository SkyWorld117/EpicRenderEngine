package epicRenderEngine;

import epicRenderEngine.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class RayTracer {

    private List<Triangle> triangles = new ArrayList<>();
    public Camera cam;
    public static final Vector3f LIGHT_DIRECTION = new Vector3f(-1.0f, -1.0f, -1.0f).normalize();

    public RayTracer(int screenWidth, int screenHeight) {
        this.cam = new Camera(screenWidth, screenHeight);
    }

    public void addTriangles(Triangle[] triangles) {
        this.triangles.addAll(List.of(triangles));
    }

    /**
     * Casts a ray at the pixel specified and returns color
     * 
     * @param p
     * @return
     */
    public void castRays(RaycasterPanel p) {
        Vector3f focalPoint = cam.getFocalPoint();

        for (int i = 0; i < cam.screenWidth; i++) {
            for (int j = 0; j < cam.screenHeight; j++) {
                int color = this.castPixel(cam.position, cam.getVecThroughPoint(focalPoint, i, j));
                p.setPixel(i, j, color);
            }
        }
    }

    /**
     * Casts a ray through pixel at i, j and returns color of intersecting Triangle.
     * If there is no intersection, returns -1.
     *
     * @return
     */
    private int castPixel(Vector3f location, Vector3f ray) {

        double minDist = Double.MAX_VALUE;
        Triangle intersecting = null;
        Triangle reflecting = null;
        Vector3f intersectionVec1 = null;
        Vector3f intersectionVec2 = null;
        double u = 0, v = 0;
        boolean light1, light2;

        for (Triangle triangle : this.triangles) {
            double[] uv = new double[2];
            intersectionVec1 = MollerTrumbore.rayIntersectsTriangle(location, ray, triangle, uv);

            if (intersectionVec1 != null) {
                double l = intersectionVec1.sub(location).length();

                if (l < minDist) {
                    minDist = l;
                    intersecting = triangle;
                    u = uv[0];
                    v = uv[1];
                }
            }
        }

        if (intersecting != null) {
            int color1, color2;
            if (intersecting.getTexture() == null) {
                color1 = intersecting.getColor();
            } else {
                color1 = intersecting.getTexture().getRGB((int) (u * (intersecting.getTexture().getWidth() - 1)),
                        (int) (v * (intersecting.getTexture().getHeight() - 1)));
            }
            if (intersecting.normal.dot(LIGHT_DIRECTION) >= 0) {
                light1 = false;
            } else {
                light1 = true;
            }


            double len=0.0;
            for (Triangle triangle : this.triangles) {
                double[] uv = new double[2];
                intersectionVec2 = MollerTrumbore.rayIntersectsTriangle(intersectionVec1, intersecting.reflect(location, ray), triangle, uv);

                if (intersectionVec2 != null) {
                    len = intersectionVec2.sub(location).length();

                    if (len < minDist) {
                        minDist = len;
                        reflecting = triangle;
                        u = uv[0];
                        v = uv[1];
                    }
                }
            }

            if (reflecting != null) {
                if (reflecting.getTexture() == null) {
                    color2 = reflecting.getColor();
                } else {
                    color2 = reflecting.getTexture().getRGB((int) (u * (reflecting.getTexture().getWidth() - 1)),
                            (int) (v * (reflecting.getTexture().getHeight() - 1)));
                }
                if (reflecting.normal.dot(LIGHT_DIRECTION) >= 0) {
                    light2 = false;
                } else {
                    light2 = true;
                }
                return getColor(light1, light2, color1, color2, getDiscount(len));
            }

            if (light1) {
                int b = (color1 >> 16) & 0x0ff;
                int g = (color1 >> 8) & 0x0ff;
                int r = color1 & 0x0ff;
                return (b << 16) | (g << 8) | (r);
            } else {
                return (0 << 16) | (0 << 8) | (0);
            }
            //return (Math.max(0, b) << 16) | (Math.max(0, g) << 8) | (Math.max(0, r));

        }

        return -1;
    }

    private int getColor(boolean l1, boolean l2, int c1, int c2, float d) {
        int b1 = (c1 >> 16) & 0x0ff;
        int g1 = (c1 >> 8) & 0x0ff;
        int r1 = c1 & 0x0ff;

        int b2 = (c2 >> 16) & 0x0ff;
        int g2 = (c2 >> 8) & 0x0ff;
        int r2 = c2 & 0x0ff;

        if (l1 && l2) {
            return (Math.min(255, b1+(int)(d*b2)) << 16) | (Math.min(255, g1+(int)(d*g2)) << 8) | (Math.min(255, r1+(int)(d*r2)));
        } else if (l1) {
            return (b1 << 16) | (g1 << 8) | (r1);
        } else if (l2) {
            return (Math.min(255, (int)(d*(b1+b2)) << 16)) | (Math.min(255, (int)(d*(g1+g2))) << 8) | (Math.min(255, (int)(d*(r1+r2))));
        } else {
            return (0 << 16) | (0 << 8) | (0);
        }
    }

    private float getDiscount(double Distance) {
        return (float) Math.exp(-0.3f*Distance);
    }

    public List<Triangle> getTriangles() {
        return this.triangles;
    }
}
