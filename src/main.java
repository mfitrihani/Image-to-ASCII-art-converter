import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        imageConverter test = new imageConverter(".idea/Resources/ascii-pineapple.jpg");

        System.out.println("Image Height: " + test.getImageHeight() + " Image Width: " + test.getImageWidth());
        System.out.println("ASCII art: ");

        test.resizeImage();
        String[][] ascii = test.getImageASCIIArt();
        for (int y = 0; y < test.getImageHeight(); y++) {
            for (int x = 0; x < test.getImageWidth(); x++) {
                System.out.print(ascii[x][y]);
            }
            System.out.println();
        }
    }
}
