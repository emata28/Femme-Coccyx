package files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class FileDownloader {
    private String link = "https://www.dropbox.com/s/raw/";
    private BufferedImage image;

    public void downloadFile(){

        try {
            System.out.print("Inserte Link de Dropbox: ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.startsWith("https://www.dropbox.com/s/")){
                link += input.substring(26);
                URL download = new URL(link);
                image = ImageIO.read(download);
                File outputfile = new File("saved.png");
                ImageIO.write(image, "png", outputfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImage(){
        return image;
    }

    public String getLink(){
        return link;
    }
}
