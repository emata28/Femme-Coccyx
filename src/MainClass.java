import files.FileDownloader;
import image.ImageAnalyzer;
import image.ImageDivider;

public class MainClass {
    public static void main(String[] args) {
        FileDownloader f = new FileDownloader();
        f.downloadFile();
        ImageAnalyzer.getInstance().AnalyzeImage(f.getLink());
        long start = System.currentTimeMillis();
        ImageDivider imageDivider = ImageDivider.getInstance();
        imageDivider.setImage(f.getImage());
        imageDivider.generateHash();
        long end = System.currentTimeMillis();
        System.out.println((end-start));
    }
}