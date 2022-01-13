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
        //Scalar product with vector vec
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector3f cross(Vector3f vec) {
        //cross product with vector vec
        float x_vec, y_vec, z_vec;
        x_vec = this.y * vec.z - this.z * vec.y;
        y_vec = this.z * vec.x - this.x * vec.z;
        z_vec = this.x * vec.y - this.y * vec.x;
        return new Vector3f(x_vec, y_vec, z_vec);
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
