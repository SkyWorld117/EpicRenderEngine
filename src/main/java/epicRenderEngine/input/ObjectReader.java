package epicRenderEngine.input;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import epicRenderEngine.util.Triangle;
import epicRenderEngine.util.Vector3f;

import javax.imageio.ImageIO;

public class ObjectReader {
    public static Triangle[] load(String str) throws IOException {
        InputStream is = ObjectReader.class.getClassLoader().getResourceAsStream(str);
        BufferedImage obame = ImageIO.read(ObjectReader.class.getClassLoader().getResource("obame.png"));

        Scanner scanner = new Scanner(is);
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

            triangles[t] = new Triangle(v0, v1, v2, color, obame);
        }
        return triangles;
    }
}