package epicRenderEngine.util;

import org.jetbrains.annotations.Nullable;

import java.util.Vector;

public class MollerTrumbore {

    private static final double EPSILON = 0.0000001;

    /**
     * Computes the point of intersection between triangle and ray using the Möller-Trumbore algorithm, returns null if there is no intersection.
     * @param rayOrigin location vector of ray
     * @param ray directional vector of ray
     * @param triangle triangle
     * @return point of intersection
     */

    /*
    The Möller-Trumbore algorithm is a fast ray-triangle intersection algorithm. We use it to find the point of intersection from the triangle of the 3D-Models.
    Link to an explanation:
    https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-rendering-a-triangle/moller-trumbore-ray-triangle-intersection
     */

    @Nullable
    public static Vector3f rayIntersectsTriangle(Vector3f rayOrigin, Vector3f ray, Triangle triangle) {
        Vector3f edge1 = triangle.v1.sub(triangle.v0);
        Vector3f edge2 = triangle.v2.sub(triangle.v0);
        Vector3f pvec = ray.cross(edge2);

        double det = edge1.dot(pvec); //determinant
        if (det > -EPSILON && det < EPSILON) {
            return null;    // This ray is parallel to this triangle.
        }
        double invDet = 1.0 / det;
        Vector3f tvec = rayOrigin.sub(triangle.v0);
        double u = invDet * (tvec.dot(pvec));

        if (u < 0.0 || u > 1.0) {
            return null;
        }

        Vector3f qvec = tvec.cross(edge1);
        double v = invDet * ray.dot(qvec);
        if (v < 0.0 || u + v > 1.0) {
            return null;
        }
        // At this stage we can compute t to find out where the intersection point is on the line.
        double t = invDet * edge2.dot(qvec);
        if (t > EPSILON) {
            return ray.scale((float)t).add(rayOrigin);
        } else {
            return null;
        }
    }
}

