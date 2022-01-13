package epicRenderEngine.input;

import java.io.File;
import java.util.Scanner;

import epicRenderEngine.util.Triangle;
import epicRenderEngine.util.Vector3f;

public class ObjectReader {
    public Triangle[] load(String str) {
        Scanner scanner = new Scanner(new File(str));
        int[] p = new int[3];
        int n = scanner.nextInt();
        Triangle[] triangles = new Triangle[n];

        for (int t = 0; t < n; t++) {
            for (int i = 0; i < 3; i++) {
                p[i] = scanner.nextInt();
            }
            Vector3f v0 = new Vector3f(p[0], p[1], p[2]);
            for (int i = 0; i < 3; i++) {
                p[i] = scanner.nextInt();
            }
            Vector3f v1 = new Vector3f(p[0], p[1], p[2]);
            for (int i = 0; i < 3; i++) {
                p[i] = scanner.nextInt();
            }
            Vector3f v2 = new Vector3f(p[0], p[1], p[2]);

            int color = scanner.nextInt();
            int red = (color >> 16) & 0xFF;
            int green = (color >> 8) & 0xFF;
            int blue = color & 0xFF;

            triangles[t] = new Triangle(v0, v1, v2, red, green, blue);
        }
        return triangles;
    }
}