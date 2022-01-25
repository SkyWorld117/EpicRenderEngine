package epicRenderEngine.util;

import java.awt.image.BufferedImage;

/**
 * class builds triangles with the three side vectors
 */
public class Triangle {

    public BufferedImage texture;
    public Vector3f v0;
    public Vector3f v1;
    public Vector3f v2;
    public Vector3f normal;

    public Vector3f c0;
    public float N, T;

    public int color;

    public Triangle(Vector3f v0, Vector3f v1, Vector3f v2, int color, BufferedImage texture) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
        this.texture = texture;
        this.normal = v1.sub(v0).cross(v2.sub(v0)).normalize();

        this.N = this.normal.dot(this.normal);
        this.T = this.v0.dot(this.normal);
        this.c0 = this.normal.scale((float) (-2.0 * this.T / this.N));
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

    public BufferedImage getTexture() {
        return texture;
    }

    public Vector3f reflect(Vector3f s, Vector3f v) {
        return this.c0.add(
                this.normal.scale(s.scale((float) (2.0 / this.N)).dot(this.normal)).add(
                        v.scale((float) ((this.T - s.dot(this.normal)) / v.dot(this.normal))))).normalize();
    }
}
