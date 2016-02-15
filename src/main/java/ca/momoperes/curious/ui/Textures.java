package ca.momoperes.curious.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Textures {
    private static HashMap<String, Image> textures = new HashMap<String, Image>();

    public static void load(String texture) {
        try {
            textures.put(texture, ImageIO.read(Textures.class.getResourceAsStream("/textures/" + texture)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image get(String texture) {
        return textures.get(texture);
    }
}
