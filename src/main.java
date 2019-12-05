import java.awt.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        imageConverter test = new imageConverter(".idea//Resources//black_cat_image.jpg");

        System.out.println("Image Height: " + test.getImageHeight() + " Image Width: " + test.getImageWidth());
//        System.out.println("Image RGB: ");

//        Color[][] imageTuple = test.getImageRGBTuple();
//        for (int y =0;y<test.getImageHeight();y++){
//            for (int x=0;x<test.getImageWidth();x++){
//                System.out.print(imageTuple[x][y]);
//            }
//            System.out.println();
//        }

        int[][] brightness = test.getImageBrightness();
        for (int y = 0; y < test.getImageHeight(); y++) {
            for (int x = 0; x < test.getImageWidth(); x++) {
                System.out.print(brightness[y][x]);
            }
            System.out.println();
        }
    }
}
