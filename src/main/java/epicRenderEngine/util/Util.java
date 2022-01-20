package epicRenderEngine.util;

public class Util {

    public static int packRGB(int r, int b, int g) {
        return r << 16 | b << 8 | g;
    }

    public static Matrix3f getRodriguezMatrix(Vector3f axis, double theta) {
        Matrix3f K = new Matrix3f(0.0f, -axis.z, axis.y,
                                    axis.z, 0.0f, -axis.x,
                                    -axis.y, axis.x, 0.0f);
        Matrix3f K2 = K.mul(K);

        return Matrix3f.getIdentity()
                .add(K.mul(((float)Math.sin(theta))))
                .add(K2.mul((float)(1.0f - Math.cos(theta))));
    }
}
