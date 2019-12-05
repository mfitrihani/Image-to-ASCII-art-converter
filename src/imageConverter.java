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

    public int[][] getImageBrightness() {
        Color[][] imageRGBTuple = getImageRGBTuple();
        int[][] pixelBrightness = new int[image.getHeight()][image.getWidth()];

        for (int x = 0;x<image.getWidth();x++){
            for (int y= 0; y<image.getHeight();y++){
                int total = new Color(image.getRGB(x,y)).getGreen()+new Color(image.getRGB(x,y)).getBlue()+new Color(image.getRGB(x,y)).getRed();
                pixelBrightness[y][x] = total/3;
            }
        }
        return pixelBrightness;
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public int getImageWidth() {
        return image.getWidth();
    }
}
