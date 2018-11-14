package image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageDivider {
    private BufferedImage image;
    private HashMap<String, Analysis> imageData = new HashMap<>();
    private ArrayList<Tag> Tags;
    private int ID = 0;


    public ImageDivider(BufferedImage pImage){
        Tags = ImageAnalyzer.getInstance().getTags();
        image = pImage;
        generateHash();
    }

    private void generateHash(){
        getRandomPixels(image);
    }

    public HashMap<String, Analysis> getImageData(){
        return imageData;
    }

    private void getRandomPixels(BufferedImage image) {
        boolean hasAlphaChannel = image.getAlphaRaster() != null;
        int width = image.getWidth();
        int heigth = image.getHeight();
        int divisions = (int) Math.log10(width*heigth);
        int Hsize = width / divisions;
        int Vsize = heigth / divisions;
        int xLeft = 0;
        int xRight = Hsize;
        int yTop = 0;
        int yBottom = Vsize;
        while(xRight <= width){
            while (yBottom <= heigth){
                int amountOfPixels = (width * heigth) / random(10, 15);
                ArrayList<String> tags = getTagsPerSection(amountOfPixels);
                while (amountOfPixels-- > 0){
                    int randX = random(xLeft, xRight);
                    int randY = random(yTop, yBottom);
                    int pixelInt = image.getRGB(randX, randY);
                    int red = pixelInt & 0xff;
                    int green = pixelInt >> 8 & 0xff;
                    int blue = pixelInt >> 16 & 0xff;
                    int alpha = 255;
                    if(hasAlphaChannel){
                        alpha = pixelInt >> 24 & 0xff;
                    }
                    PixelSample pixel = new PixelSample(alpha, red, green, blue, ID++, randX, randY, tags.get(0));
                    tags.remove(0);
                    addToHash(pixel);
                }
                yTop = yBottom + 1;
                yBottom += Vsize;
            }
            xLeft = xRight + 1;
            xRight += Hsize;

            yTop = 0;
            yBottom = Vsize;
        }
    }

    private void addToHash(PixelSample pixel){
        if(imageData.containsKey(pixel.toString())){
            Analysis analysis = imageData.get(pixel.toString());
            analysis.addSample(pixel);
            imageData.replace(pixel.toString(), analysis);
        } else {
            Analysis analysis = new Analysis(pixel);
            imageData.put(pixel.toString(),analysis);
        }
    }

    private ArrayList<String> getTagsPerSection(int amountOfPixels) {
        ArrayList<String> tagsPerSection = new ArrayList<>();
        Double sum = 0.0;
        for (Tag tag : Tags) {
            sum += tag.getConfidence();
        }
        for (Tag tag : Tags) {
            Double confidence = tag.getConfidence();
            int amount = (int) (amountOfPixels * (confidence / sum));
            while (amount-- >= 0) {
                tagsPerSection.add(tag.getName());
            }
        }
        return tagsPerSection;
    }

    private int random(int min, int max) {
        int range = (max - min);
        return (int) (Math.random() * range) + min;
    }
}