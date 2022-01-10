package epicRenderEngine.util;

public class MollerTrumbore {

    private static final double EPSILON = 0.0000001;

    public static boolean rayIntersectsTriangle(Vector3f rayOrigin, Vector3f rayVector, Triangle inTriangle, Vector3f outIntersectionPoint) {
        Vector3f edge1 = new Vector3f();
        Vector3f edge2 = new Vector3f();
        Vector3f h = new Vector3f();
        Vector3f s = new Vector3f();
        Vector3f q = new Vector3f();
        double a, f, u, v;
        edge1.sub(inTriangle.v1, inTriangle.v0);
        edge2.sub(inTriangle.v2, inTriangle.v0);
        h.cross(rayVector, edge2);
        a = edge1.dot(h);
        if (a > -EPSILON && a < EPSILON) {
            return false;    // This ray is parallel to this triangle.
        }
        f = 1.0 / a;
        s.sub(rayOrigin, inTriangle.v0);
        u = f * (s.dot(h));
        if (u < 0.0 || u > 1.0) {
            return false;
        }

        q.cross(s, edge1);
        v = f * rayVector.dot(q);
        if (v < 0.0 || u + v > 1.0) {
            return false;
        }
        // At this stage we can compute t to find out where the intersection point is on the line.
        double t = f * edge2.dot(q);
        if (t > EPSILON) {
            outIntersectionPoint.set(0.0, 0.0, 0.0);
            outIntersectionPoint.scaleAdd(t, rayVector, rayOrigin);
            return true;
        } else {
            return false;
        }
    }
}

