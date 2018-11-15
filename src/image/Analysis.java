package image;

import java.util.ArrayList;

public class Analysis {
    private int count =0;
    private PixelSample sample;
    private ArrayList<Integer> coordinates;
    private String tag;
    private int Id;


    public Analysis(PixelSample psample, int pX, int pY) {
        sample = psample;
        count++;
        coordinates = new ArrayList<>();
        coordinates.add(pX);
        coordinates.add(pY);
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void addSample(int X, int Y){
        coordinates.add(X);
        coordinates.add(Y);
        count++;
    }

    public PixelSample getSample() {
        return sample;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
