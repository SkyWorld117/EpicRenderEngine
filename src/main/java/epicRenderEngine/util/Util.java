package epicRenderEngine.util;

public class Util {

    public static int packRGB(int r, int b, int g) {
        return r << 16 | b << 8 | g;
    }
}
