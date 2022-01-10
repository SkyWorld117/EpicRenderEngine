package epicRenderEngine.util;

public class Vector3f {
    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    float x = 0, y = 0, z = 0;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float dot(Vector3f vec) {
        return 0;
    }

    public Vector3f cross(Vector3f vec) {
        return ZERO;
    }

    public Vector3f add(Vector3f... vecs) {
        return ZERO;
    }

    public Vector3f sub(Vector3f... vecs) {
        return ZERO;
    }

    public Vector3f scale(float scalar) {
        return ZERO;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
