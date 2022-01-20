package epicRenderEngine.util;

public class Matrix3f {
    private float f00;
    private float f01;
    private float f02;
    private float f10;
    private float f11;
    private float f12;
    private float f20;
    private float f21;
    private float f22;

    public Matrix3f(float f00, float f01, float f02, float f10, float f11, float f12, float f20, float f21, float f22) {
        this.f00 = f00;
        this.f01 = f01;
        this.f02 = f02;
        this.f10 = f10;
        this.f11 = f11;
        this.f12 = f12;
        this.f20 = f20;
        this.f21 = f21;
        this.f22 = f22;
    }

    public static Matrix3f getIdentity() {
        return new Matrix3f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Matrix3f mul(Matrix3f matrix) {
        float f00, f01, f02, f10, f11, f12, f20, f21, f22;
        f00 = this.f00*matrix.f00 + this.f01*matrix.f10 + this.f02*matrix.f20;
        f01 = this.f00*matrix.f01 + this.f01*matrix.f11 + this.f02*matrix.f21;
        f02 = this.f00*matrix.f02 + this.f01*matrix.f12 + this.f02*matrix.f22;

        f10 = this.f10*matrix.f00 + this.f11*matrix.f10 + this.f12*matrix.f20;
        f11 = this.f10*matrix.f01 + this.f11*matrix.f11 + this.f12*matrix.f21;
        f12 = this.f10*matrix.f02 + this.f11*matrix.f12 + this.f12*matrix.f22;

        f20 = this.f20*matrix.f00 + this.f21*matrix.f10 + this.f22*matrix.f20;
        f21 = this.f20*matrix.f01 + this.f21*matrix.f11 + this.f22*matrix.f21;
        f22 = this.f20*matrix.f02 + this.f21*matrix.f12 + this.f22*matrix.f22;

        return new Matrix3f(f00, f01, f02, f10, f11, f12, f20, f21, f22);
    }

    public Matrix3f mul(float s) {
        return new Matrix3f(
                f00 * s, f01 * s, f02 * s,
                f10 * s, f11 * s, f12 * s,
                f20 * s, f21 * s, f22 * s);
    }

    public Vector3f mul(Vector3f vec) {
        return new Vector3f(
                vec.x * f00 + vec.y * f01 + vec.z * f02,
                vec.x * f10 + vec.y * f11 + vec.z * f12,
                vec.x * f20 + vec.y * f21 + vec.z * f22);
    }

    public Matrix3f add(Matrix3f matrix) {
        this.f00 += matrix.f00;
        this.f01 += matrix.f01;
        this.f02 += matrix.f02;

        this.f10 += matrix.f10;
        this.f11 += matrix.f11;
        this.f12 += matrix.f12;

        this.f20 += matrix.f20;
        this.f21 += matrix.f21;
        this.f22 += matrix.f22;

        return this;
    }
}
