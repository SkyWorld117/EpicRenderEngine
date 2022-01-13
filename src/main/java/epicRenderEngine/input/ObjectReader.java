package epicRenderEngine.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import epicRenderEngine.util.Triangle;
import epicRenderEngine.util.Vector3f;

public class ObjectReader {
    public ArrayList<Triangle> load(String str) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(str));
        int color = 0;
        int[] p = new int[3];
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();

        while (scanner.hasNextInt()) {
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

            color = scanner.nextInt();
            int red = (color >> 16) & 0xFF;
            int green = (color >> 8) & 0xFF;
            int blue = color & 0xFF;

            triangles.add(new Triangle(v0, v1, v2, red, green, blue));
        }
        return triangles;
    }
}