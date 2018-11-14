import files.FileDownloader;
import image.ImageAnalyzer;
import image.ImageDivider;

public class MainClass {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FileDownloader f = new FileDownloader();
        f.downloadFile();
        ImageAnalyzer.getInstance().AnalyzeImage(f.getLink());
        ImageDivider saasd = new ImageDivider(f.getImage());
        long end = System.currentTimeMillis();
        System.out.println((end-start));
    }
}
