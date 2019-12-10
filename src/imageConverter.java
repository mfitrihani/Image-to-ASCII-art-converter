import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imageConverter {
    private BufferedImage image;
    private Color[][] imageRGB = null;
    private Double[][] imageBrightness = null;
    private String[][] ASCIIArt = null;
    private String[] ASCII =
            {"`", "^", ",", ":", ";", "I", "l", "!", "i",
                    "~", "+", "_", "-", "?", "]", "[", "}", "{", "1",
                    ")", "(", "|", "/", "t", "f", "j", ":", "r",
                    "x", "n", "u", "v", "c", "z", "X", "Y", "U", "J",
                    "C", "L", "Q", "0", "O", "Z", "m", "w", "q", "p",
                    "d" , "b", "k", "h", "a", "o", "*", "#", "M", "W",
                    "&", "8", "%", "B", "@", "$"};

    public imageConverter(String imagePath) throws IOException {
        image = ImageIO.read(new File(imagePath));
    }

    public void resizeImage(){
        BufferedImage newImage = new BufferedImage(image.getWidth()/5, image.getHeight()/5, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.createGraphics();
        g.drawImage(image, 0, 0, image.getWidth()/5, image.getHeight()/5, null);
        g.dispose();
        image = newImage;
    }

    public void resizeImage(int width, int height){
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        image = newImage;
    }

    public Color[][] getImageRGB() {
        //check if imageRGB has value
        if (imageRGB==null)
            generateImageRGB();
        return imageRGB;
    }

    public void generateImageRGB(){
        //get image RGB value and store them in imageRGB array
        imageRGB = new Color[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageRGB[x][y] = new Color(image.getRGB(x, y));
            }
        }
    }

    public Double[][] getImageBrightness() {
        //check if imageBrightness has value
        if (imageBrightness == null)
            generateImageBrightness();
        return imageBrightness;
    }

    public void generateImageBrightness(){
        //get brightness using RGB -> Luma conversion formula
        //Y = 0.2126 R + 0.7152 G + 0.0722 B
        imageBrightness = new Double[image.getWidth()][image.getHeight()];
        Color[][] rgbValue = getImageRGB();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                imageBrightness[x][y] = (double) (rgbValue[x][y].getRed()*0.2126+rgbValue[x][y].getGreen()*0.7152+rgbValue[x][y].getBlue()*0.0722);
            }
        }
    }

    public String[][] getImageASCIIArt() {
        //check if ASCIIArt has value
        if (ASCIIArt==null)
            generateImageASCIIArt();
        return ASCIIArt;
    }

    public void generateImageASCIIArt(){
        ASCIIArt = new String[image.getWidth()][image.getHeight()];
        Double[][] imageBrightness = getImageBrightness();
        double divider = (double)ASCII.length/(double)255;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if ((imageBrightness[x][y]*divider)%1>0.5){
                    ASCIIArt[x][y] = String.format("%1$s%1$s%1$s",ASCII[(int) Math.ceil(imageBrightness[x][y]*divider)]);
                }
                else
                    ASCIIArt[x][y] = String.format("%1$s%1$s%1$s",ASCII[(int) Math.floor(imageBrightness[x][y]*divider)]);
            }
        }
    }

    public void invertImageBrightness() {
        Double[][] brightness = getImageBrightness();
        Double[][] temp = new Double[image.getWidth()][image.getHeight()];
        //invert brightness value
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                temp[x][y] = 255 - brightness[x][y];
            }
        }
        imageBrightness = temp;
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public int getImageWidth() {
        return image.getWidth();
    }

}
