package epicRenderEngine.util;

public class Vector3f {
    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    float x = 0, y = 0, z = 0;

    public Vector3f(float x, float y, float z) {
        //Constructor
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

    public Vector3f add(Vector3f vec) {
        //adds two vectors
        return new Vector3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector3f sub(Vector3f vec) {
        //subtracts vector vec
        return new Vector3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public Vector3f scale(float scalar) {
        //scales vector
        return new Vector3f(scalar * this.x, scalar * this.y, scalar * this.z);
    }

    public void set(float x, float y, float z) {
        //Set values of the vector
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f addX(float dx) {
        //adds dx to x-coordinate
        return new Vector3f(this.x + dx, this.y, this.z);
    }

    public Vector3f addY(float dy) {
        //adds dy to y-coordinate
        return new Vector3f(this.x, this.y + dy, this.z);
    }

    public Vector3f normalize() {
        //normalize the vector to length 1
        float length = (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vector3f(this.x / length, this.y / length, this.z / length);
    }
}
