
// author : connor chang
// purpose : class to represent a letter on the board, contains some graph theory vertex characteristics (adjacents)

import java.lang.Math;

public class BoogleLetter {

  // class variables
  private static String[] allLetters = new String [] {"I","E","T","U","O","A","N","R","L","D","S","M","C","P","QU","X","V","G","F","B","H","Z","Y","W","K","J"};
  private static int[] letterWeights = new int [] {8,8,8,7,7,7,6,5,5,4,4,3,3,2,2,2,2,2,2,2,2,1,1,1,1,1};

  private String letter;
  private BoogleLetter[] adj;
  private int deg;

  /**
  default, randomized constructor
  **/
  public BoogleLetter() {

    // weighting stuff
    int totalWeight = 0;
    for(int weight : letterWeights) {
      totalWeight += weight;
    }

    int i = 0;
    for(int rand = (int) (Math.random() * totalWeight); i < allLetters.length; i++) {
      rand -= letterWeights[i];
      if(rand <= 0) { break; }
    }

    this.letter = allLetters[i];
    this.adj = new BoogleLetter[8];
    this.deg = 0;

  }

  /**
  parametrized constructor, starts as given letter; mainly for troubleshooting
  @param letter the letter-to-be
  **/
  public BoogleLetter(String letter) {
    this.letter = letter;
    this.adj = new BoogleLetter[8];
    this.deg = 0;
  }

  /**
  adds an adjacent "BoogleLetter" vertex to the array
  @param l the letter to add as an adjacent
  **/
  public void addAdjacent(BoogleLetter l) {
    // if adjacent is full for some reason
    if(deg >= 8) return;

    adj[deg] = l;
    deg++;
  }

  /**
  accessor for going through the adjacents by index
  @param i index of the adjacent letter to return
  **/
  public BoogleLetter getAdjacent(int i) {
    return this.adj[i];
  }

  /**
  degree accessor
  **/
  public int getDegree() {
    return deg;
  }

  /**
  tells if this is a normal letter or annoying edge case (QU i am talking about you)
  **/
  public boolean isOneLetter() {
    return this.letter.length() == 1;
  }

  /**
  toString override. also plan to use as an accessor for this.letter
  **/
  @Override
  public String toString() {
    return this.letter;
  }
}
