package epicRenderEngine.util;

/**
 * class builds triangles with the three side vectors
 */
public class Triangle {
    public Vector3f v0;
    public Vector3f v1;
    public Vector3f v2;
    public int color;

    public Triangle(Vector3f v0, Vector3f v1, Vector3f v2, int color) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
