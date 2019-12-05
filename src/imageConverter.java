import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imageConverter {
    private BufferedImage image = null;

    public imageConverter(String imagePath) throws IOException {
        image = ImageIO.read(new File(imagePath)); //set buffered image
    }

    public Color[][] getImageRGBTuple() {
        Color[][] imageTuple = new Color[image.getWidth()][image.getHeight()]; //create an array with size according to image size

        //get image RGB value and store them the array
        for(int y=0;y<image.getWidth();y++){
            for (int x=0;x<image.getHeight();x++){
                imageTuple[y][x] = new Color(image.getRGB(y,x));
            }
        }

        return imageTuple;
    }

//    public void getImageBrightness() {
//        int[][] imageRGBTuple = getImageRGBTuple();
//        int[][] pixelBrightness = new int[imageRGBTuple[0].length][imageRGBTuple.length];
//
//        for (int y = 0;y<imageRGBTuple.length;y++){
//            for (int x= 0; x<imageRGBTuple[0].length;x++){
//                pixelBrightness[x][y] =
//            }
//        }
//    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public int getImageWidth() {
        return image.getWidth();
    }
}
