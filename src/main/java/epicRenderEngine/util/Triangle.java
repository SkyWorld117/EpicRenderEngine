package epicRenderEngine.util;

/**
 * class builds triangles with the three side vectors
 */
public class Triangle {
    public Vector3f v0;
    public Vector3f v1;
    public Vector3f v2;
    public Vector3f normal;

    public int color;

    public Triangle(Vector3f v0, Vector3f v1, Vector3f v2, int color) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
        this.normal = v1.sub(v0).cross(v2.sub(v0)).normalize();
    }

    public int getColor() {
        return this.color;
    }

    public void applyTransform(Matrix3f m) {
        this.v0 = m.mul(this.v0);
        this.v1 = m.mul(this.v1);
        this.v2 = m.mul(this.v2);

        this.normal = v1.sub(v0).cross(v2.sub(v0)).normalize();
    }
}
