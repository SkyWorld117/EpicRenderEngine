package epicRenderEngine.util;

public class Triangle {
    public Vector3f v0;
    public Vector3f v1;
    public Vector3f v2;

    public void Triangle(Vector3f v0, Vector3f v1, Vector3f v2) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getColor() {
        return 0;
    }
}
