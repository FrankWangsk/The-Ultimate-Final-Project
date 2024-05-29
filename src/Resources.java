import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Resources {

    public static Image MY_JET = readImage("src/myjet.jpg");
    public static Image ENEMY_JET = readImage("src/enemyjet.jpg");
    public static Image BIG_SHIELD = readImage("src/bigshield.png");
    public static Image SMALL_SHIELD = readImage("src/smallshield.png");



    private static Image readImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
