import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        imageConverter test = new imageConverter(".idea/Resources/black_cat_image.jpg");
        test.resizeImage(200, 200);
        test.writeAsciiToTextFile();
    }
}
