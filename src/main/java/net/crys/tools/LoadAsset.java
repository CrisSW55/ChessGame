package net.crys.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadAsset {
    public static final String CHESS_ATLAS = "chessAtlas.png";

    public static BufferedImage LoadAtlas(String strAtlas){
        BufferedImage img = null;
        InputStream is = LoadAsset.class.getResourceAsStream("/"+strAtlas);
        try{
            img = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return img;
    }
}
