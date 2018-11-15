package image;

public class PixelSample {
    private int Alpha;
    private int Red;
    private int Green;
    private int Blue;
    //private int ID;
   // private String Tag;

    public PixelSample(int alpha, int red, int green, int blue) {
        Alpha = alpha;
        Red = red;
        Green = green;
        Blue = blue;

    }

    public int getAlpha() {
        return Alpha;
    }

    public int getRed() {
        return Red;
    }

    public int getGreen() {
        return Green;
    }

    public int getBlue() {
        return Blue;
    }



    @Override
    public String toString() {
        return Alpha+" "+Red+" "+Green+" "+Blue;
    }
}
