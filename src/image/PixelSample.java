package image;

public class PixelSample {
    private int Alpha;
    private int Red;
    private int Green;
    private int Blue;
    private int ID;
    private int x;
    private int y;
    private String Tag;

    public PixelSample(int alpha, int red, int green, int blue, int pID, int pX, int pY, String tag) {
        Alpha = alpha;
        Red = red;
        Green = green;
        Blue = blue;
        ID = pID;
        x = pX;
        y = pY;
        Tag = tag;
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

    public int getID() {
        return ID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTag() {
        return Tag;
    }

    @Override
    public String toString() {
        return Alpha+" "+Red+" "+Green+" "+Blue;
    }
}
