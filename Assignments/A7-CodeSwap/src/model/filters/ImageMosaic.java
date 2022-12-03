package model.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import model.GridPixelImage;
import model.Pixel;
import model.PixelImage;
import model.StdPixel;

/**
 *
 */
public class ImageMosaic implements IMosaic {
  private final PixelImage image;
  private ArrayList<ArrayList<Integer>> seeds;
  private HashMap<ArrayList<Integer>, ArrayList<Pixel>> clusters;

  /**
   *
   * @param image
   * @param number
   */
  public ImageMosaic(PixelImage image, int number) {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    if (number < 0) {
      throw new IllegalArgumentException("Negative number of seeds");
    }
    this.image = image;
    this.seeds = this.createSeeds(number); // initialize with helper method
    this.clusters = this.createClusters(); // initialize with helper method
    this.setClusters();
  }

  /**
   *
   * @param numberOfSeeds
   * @return
   */
  // calculates random numberOfSeeds of seeds
  private ArrayList<ArrayList<Integer>> createSeeds(int numberOfSeeds) {
    ArrayList<ArrayList<Integer>> listOfSeeds = new ArrayList<>();
    Random rand = new Random();
    for (int i = 0; i < numberOfSeeds; i++) {
      // y-value
      int row = rand.nextInt(image.getHeight() - 1);
      // x-value
      int col = rand.nextInt(image.getWidth() - 1);
      listOfSeeds.add(new ArrayList<Integer>(Arrays.asList(row, col)));
    }
    return listOfSeeds;
  }

  /**
   *
   * @return
   */
  // initializes empty cluster
  private HashMap<ArrayList<Integer>, ArrayList<Pixel>> createClusters() {
    HashMap<ArrayList<Integer>, ArrayList<Pixel>> clusterMap = new HashMap<>();
    for (ArrayList<Integer> seed : this.seeds) {
      clusterMap.put(seed, new ArrayList<>());
    }

    return clusterMap;
  }

  /**
   *
   * @param col1
   * @param col2
   * @param row1
   * @param row2
   * @return
   */
  // calculates distance between two points
  private double distanceFormula(int col1, int col2, int row1, int row2) {
    return Math.abs(Math.sqrt(Math.pow((row2 - row1), 2) + Math.pow((col2 - col1), 2)));
  }

  /**
   *
   * @param row
   * @param col
   * @return
   */
  // returns seed with the minimum distance with GIVEN point
  private ArrayList<Integer> returnSeed(int row, int col) {
    double minDist = distanceFormula(this.seeds.get(0).get(1), col, this.seeds.get(0).get(0), row);
    int counter = 0;
    for (int i = 1; i < this.seeds.size(); i++) {
      if (distanceFormula(this.seeds.get(i).get(1), col, this.seeds.get(i).get(0), row) < minDist) {
        minDist = (int) distanceFormula(this.seeds.get(i).get(1), col, this.seeds.get(i).get(0), row);
        counter = i;
      }
    }
    return this.seeds.get(counter);
  }

  /**
   *
   */
  // create clusters for each seeds
  private void setClusters() {
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        this.clusters.get(this.returnSeed(row, col)).add(this.image.getPixelAt(row, col));
      }
    }
  }

  /**
   *
   * @param pixelList
   * @return
   */
  // returns pixel with average color from cluster
  private Pixel newAveragePixel(ArrayList<Pixel> pixelList) {
    int redSum = 0;
    int greenSum = 0;
    int blueSum = 0;
    for (Pixel p : pixelList) {
      redSum += p.getRed();
      greenSum += p.getGreen();
      blueSum += p.getBlue();
    }
    return new StdPixel(redSum / pixelList.size(), greenSum / pixelList.size(),
            blueSum / pixelList.size(), 255);
  }

  // create new mosaic image
  public PixelImage createMosaic() {
    Pixel[][] mosaicArray = new Pixel[this.image.getHeight()][this.image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        mosaicArray[row][col] = this.newAveragePixel(this.clusters.get(returnSeed(row, col)));
      }
    }
    return new GridPixelImage(mosaicArray,this.image.getHeight(), this.image.getWidth());
  }
}
