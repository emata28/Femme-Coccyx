package image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class ImageDivider {
    private BufferedImage image;
    private HashMap<Integer, HashMap<String,Analysis>> BigAssHash = new HashMap<>();
    private ArrayList<Tag> Tags;
    private int ID = 0;
    private boolean hasAlphaChannel;
    private int width;
    private int heigth;

    private static ImageDivider ourInstance = new ImageDivider();
    public static ImageDivider getInstance() {
        return ourInstance;
    }
    private ImageDivider() {
        Tags = ImageAnalyzer.getInstance().getTags();
    }

    public void setImage(BufferedImage pImage){
        image = pImage;
        hasAlphaChannel = image.getAlphaRaster() != null;
        width = image.getWidth();
        heigth = image.getHeight();
    }

    public void generateHash(){
        try {
            int divisions = (int) Math.log10(width*heigth);
            int Hsize = width / divisions;
            int Vsize = heigth / divisions;
            int xLeft = 0;
            int xRight = Hsize;
            int yTop = 0;
            int yBottom = Vsize;
            ExecutorService service = Executors.newFixedThreadPool(10);
            int cantSec = 1;
            while(xRight <= width){
                while (yBottom <= heigth){
                    service.execute(new RandPixelGen(xLeft, xRight, yTop, yBottom, cantSec));
                    yTop = yBottom + 1;
                    yBottom += Vsize;
                    cantSec++;
                }
                xLeft = xRight + 1;
                xRight += Hsize;

                yTop = 0;
                yBottom = Vsize;
            }
            service.shutdown();
            service.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("NOW");

    }

    public int getID(){
        //System.out.println(ID);
        return ID++;
    }

    public void AddHashToHash(HashMap<String,Analysis> pHash, int pId){
                BigAssHash.put(pId,pHash);
    }

    public BufferedImage getImage() {
        return image;
    }


    public boolean isHasAlphaChannel() {
        return hasAlphaChannel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public ArrayList<Tag> getTags() {
        return Tags;
    }
}
