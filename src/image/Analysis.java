package image;

import java.util.ArrayList;

public class Analysis {
    private ArrayList<PixelSample> samples = new ArrayList<>();
    private int count;

    public Analysis(PixelSample sample) {
        this.samples.add(sample);
        this.count = samples.size();
    }

    public int getCount() {
        return count;
    }

    public ArrayList<PixelSample> getSamples() {
        return samples;
    }

    public void addSample(PixelSample sample){
        samples.add(sample);
        count++;
    }
}
