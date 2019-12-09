import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class imageConverter {
    private BufferedImage image = null;
    private String[] ASCII =
            {"`", "^", ",", ":", ";", "I", "l", "!", "i",
                    "~", "+", "_", "-", "?", "]", "[", "}", "{", "1",
                    ")", "(", "|", "/", "t", "f", "j", ":", "r",
                    "x", "n", "u", "v", "c", "z", "X", "Y", "U", "J",
                    "C", "L", "Q", "0", "O", "Z", "m", "w", "q", "p",
                    "d" , "b", "k", "h", "a", "o", "*", "#", "M", "W",
                    "&", "8", "%", "B", "@", "$"};

    public imageConverter(String imagePath) throws IOException {
        image = ImageIO.read(new File(imagePath)); //set buffered image
    }

    public Color[][] getImageRGBTuple() {
        Color[][] imageTuple = new Color[image.getWidth()][image.getHeight()]; //create an array with size according to image size

        //get image RGB value and store them the array
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageTuple[x][y] = new Color(image.getRGB(x, y));
            }
        }

        return imageTuple;
    }

    public int[][] getImageBrightness() {
        Color[][] imageRGBTuple = getImageRGBTuple();
        int[][] pixelBrightness = new int[image.getWidth()][image.getHeight()];

        //get brightness by getting average of RGB Value
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pixelBrightness[x][y] = (imageRGBTuple[x][y].getRed() + imageRGBTuple[x][y].getGreen() + imageRGBTuple[x][y].getBlue()) / 3;
            }
        }
        return pixelBrightness;
    }

    public String[][] getImageASCIIArt() {
        String[][] convertedArt = new String[image.getWidth()][image.getHeight()];
        int[][] imageBrightness = getImageBrightness();
        double divider = (double)ASCII.length/(double)255;;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if ((imageBrightness[x][y]*divider)%1>0.5){
                    convertedArt[x][y] = ASCII[(int) Math.ceil(imageBrightness[x][y]*divider)];
                }
                else
                    convertedArt[x][y] = ASCII[(int) Math.floor(imageBrightness[x][y]*divider)];
            }
        }


        return convertedArt;
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public int getImageWidth() {
        return image.getWidth();
    }
}
