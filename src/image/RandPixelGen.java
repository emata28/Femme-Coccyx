package image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class RandPixelGen implements Runnable {
    private HashMap<String,Analysis> RegionHash = new HashMap<>();
    private int xL;
    private int xR;
    private int yT;
    private int yB;
    private BufferedImage image;
    private int width;
    private int heigth;
    private boolean hasAlphaChannel;
    private int id;
    private ArrayList<Tag> Tags;
    private int CurrentTag;

    public RandPixelGen(int xL, int xR, int yT, int yB,int pId){
        ImageDivider imageDivider = ImageDivider.getInstance();
        this.image = imageDivider.getImage();
        this.heigth = imageDivider.getHeigth();
        this.width = imageDivider.getWidth();
        this.xL = xL;
        this.xR = xR;
        this.yB = yB;
        this.yT = yT;
        this.hasAlphaChannel = imageDivider.isHasAlphaChannel();
        this.id = pId;
        this.Tags = imageDivider.getTags();
        this.CurrentTag = 0;

    }

    @Override
    public void run() {
        ImageDivider imageDivider = ImageDivider.getInstance();
        int amountOfPixels = (width * heigth) / random(10, 15);

        while (amountOfPixels-- > 0){
            int randX = random(xL, xR);
            int randY = random(yT, yB);


            AddToHash(randX,randY);

        }
        imageDivider.AddHashToHash(RegionHash,id);
    }

    private int random(int min, int max) {
        int range = (max - min);
        return (int) (Math.random() * range) + min;
    }

    private void AddToHash(int pX, int pY){
        try {
            int pixelInt = image.getRGB(pX, pY);
            int red = pixelInt & 0xff;
            int green = pixelInt >> 8 & 0xff;
            int blue = pixelInt >> 16 & 0xff;
            int alpha = 255;
            if(hasAlphaChannel){
                alpha = pixelInt >> 24 & 0xff;
            }
            String argb = alpha+" "+red+" "+green+" "+blue;
            if(RegionHash.containsKey(argb)){
                Analysis analysis = RegionHash.get(argb);
                analysis.addSample(pX,pY);
            }
            else {
                PixelSample pixel = new PixelSample(alpha, red, green, blue);
                Analysis analysis = new Analysis(pixel, pX, pY);
                analysis.setTag(Tags.get(CurrentTag++ % Tags.size()).getName());
                RegionHash.put(pixel.toString(),analysis);
            }
        } catch (Exception e) {
            System.out.println("mame en el " + id);
        }
    }


}
