package model.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.GridPixelImage;
import model.Pixel;
import model.PixelImage;
import model.StdPixel;

/**
 * Class that represents the Image Mosaic functionality that can be applied to an image.
 */
public class ImageMosaic implements IMosaic {
  private final PixelImage image;
  private ArrayList<ArrayList<Integer>> seeds;
  private HashMap<ArrayList<Integer>, ArrayList<Pixel>> clusters;
  int rand;
  boolean haveRandSeed = false;

  /**
   * Constrructor for the Image Mosaic functionality.
   * @param image the given image to be changed.
   * @param number the number of seeds the user specifies.
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

  public ImageMosaic(PixelImage image, int number, int random) {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    if (number < 0) {
      throw new IllegalArgumentException("Negative number of seeds");
    }
    this.image = image;
    this.rand = random;
    this.haveRandSeed = true;
    this.seeds = this.createSeeds(number); // initialize with helper method
    this.clusters = this.createClusters(); // initialize with helper method
    this.setClusters();
  }


  /**
   * Method that creates the random seeds based on the number inputted by the user.
   * @param numberOfSeeds number of seeds specified by the user.
   * @return list of all the seeds created.
   */
  // calculates random numberOfSeeds of seeds
  private ArrayList<ArrayList<Integer>> createSeeds(int numberOfSeeds) {
    Set<ArrayList<Integer>> setOfSeeds = new HashSet<>();
    while (setOfSeeds.size() < numberOfSeeds) {
      if (!haveRandSeed) {
        int row = new Random().nextInt(image.getHeight() - 1);
        // x-value
        int col = new Random().nextInt(image.getWidth() - 1);
        setOfSeeds.add(new ArrayList<Integer>(Arrays.asList(row, col)));
      }
      else {
        // y-value
        Random rand = new Random(this.rand);
        int row = rand.nextInt(image.getHeight() - 1);
        // x-value
        int col = rand.nextInt(image.getWidth() - 1);
        setOfSeeds.add(new ArrayList<Integer>(Arrays.asList(row, col)));
        this.rand = this.rand + 1000;
      }
    }
    ArrayList<ArrayList<Integer>> listOfSeeds = new ArrayList<>(setOfSeeds);
    return listOfSeeds;
  }

  /**
   * Method that initailizes the clusters with empty ArrayList.
   * @return Hashmap that maps seeds to ArrayList of Pixels (empty values).
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
   * Method that calculates the distance between two points.
   * @param col1 col value of first point.
   * @param col2 col value of second point.
   * @param row1 row value of first point.
   * @param row2 row vale of second point.
   * @return double that represents the distance between two points.
   */
  // calculates distance between two points
  private double distanceFormula(int col1, int col2, int row1, int row2) {
    return Math.abs(Math.sqrt(Math.pow((row2 - row1), 2) + Math.pow((col2 - col1), 2)));
  }

  /**
   * Method that returns seed that is closest in distance to a minimum point.
   * @param row given pixel's row.
   * @param col given pixel's col.
   * @return ArrayList that represents correct seed.
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

  @Override
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
